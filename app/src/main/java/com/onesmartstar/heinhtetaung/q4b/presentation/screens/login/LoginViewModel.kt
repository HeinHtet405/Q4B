package com.onesmartstar.heinhtetaung.q4b.presentation.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onesmartstar.heinhtetaung.q4b.data.repository.DataStoreRepository
import com.onesmartstar.heinhtetaung.q4b.data.repository.MainRepository
import com.onesmartstar.heinhtetaung.q4b.util.Constants.ERROR_RESPONSE
import com.onesmartstar.heinhtetaung.q4b.util.Constants.SUCCESS_RESPONSE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage

    fun validationFields(valueId: String, password: String): Boolean {
        if (valueId.isNotEmpty() && password.isNotEmpty()) {
            return true
        }
        return false
    }

    fun setLogin(valueId: String, password: String) {
        viewModelScope.launch {
            try {
                val loginData = mainRepository.login(
                    valueId = valueId,
                    password = password
                )
                _errorMessage.value = SUCCESS_RESPONSE
                val user = loginData.userInfo
                saveToken(user.token)
            } catch (e: Exception) {
                _errorMessage.value = ERROR_RESPONSE
            }
        }
    }

    fun saveToken(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.saveToken(token = token)
        }
    }

}