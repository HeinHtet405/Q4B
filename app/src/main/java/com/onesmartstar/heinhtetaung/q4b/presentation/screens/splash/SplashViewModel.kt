package com.onesmartstar.heinhtetaung.q4b.presentation.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onesmartstar.heinhtetaung.q4b.data.repository.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    private val _checkToken = MutableStateFlow("")
    val checkToken: StateFlow<String> = _checkToken

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _checkToken.value = dataStoreRepository
                .readToken()
                .stateIn(viewModelScope)
                .value
        }
    }
}