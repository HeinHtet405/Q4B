package com.onesmartstar.heinhtetaung.q4b.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.onesmartstar.heinhtetaung.q4b.ui.theme.AccentColor

@Composable
fun CustomInputField(
    type: String = "uid",
    inputValue: MutableState<String> = remember {
        mutableStateOf("")
    },
    seePasswordToggle: MutableState<Boolean> = remember {
        mutableStateOf(false)
    }
) {

    TextField(
        value = inputValue.value,
        onValueChange = { inputValue.value = it },
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp),
        label = {
            if (type == "uid")
                Text(text = "Login", color = Color.Gray)
            else
                Text(text = "Password", color = Color.Gray)
        },
        visualTransformation =
        if (type == "password") {
            if (!seePasswordToggle.value) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            }
        } else {
            VisualTransformation.None
        },
        trailingIcon = {
            if (type == "password") Icon(
                imageVector = if (seePasswordToggle.value) Icons.Rounded.Visibility
                else Icons.Rounded.VisibilityOff,
                contentDescription = "Trailing Icon",
                tint = AccentColor,
                modifier = Modifier
                    .size(20.dp)
                    .clip(shape = RoundedCornerShape(4.dp))
                    .clickable { seePasswordToggle.value = !seePasswordToggle.value }

            )
        },
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Gray,
            disabledTextColor = Color.Gray,
            backgroundColor = Color.White,
            focusedIndicatorColor = AccentColor,
            unfocusedIndicatorColor = Color.Gray,
            disabledIndicatorColor = Color.Gray
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = if (type == "uid") KeyboardType.Email else KeyboardType.Number,
            imeAction = if (type == "uid") ImeAction.Next else ImeAction.Go
        )
    )
}