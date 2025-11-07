package ru.itis.effectivemobiletesttask.feature_auth.impl

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _isLoginEnabled = MutableStateFlow(false)
    val isLoginEnabled: StateFlow<Boolean> = _isLoginEnabled

    private val _navigation = MutableSharedFlow<LoginNavigationEvent>()
    val navigation = _navigation.asSharedFlow()

    fun onEmailChanged(email: String) {
        _email.value = email
        validateFields()
    }

    fun onPasswordChanged(password: String) {
        _password.value = password
        validateFields()
    }

    fun onLoginClicked() {
        viewModelScope.launch {
            _navigation.emit(LoginNavigationEvent.ToMainScreen)
        }
    }

    private fun validateFields() {
        val isValidEmail = isValidEmail(_email.value)
        val isPasswordFilled = _password.value.isNotEmpty()
        _isLoginEnabled.value = isValidEmail && isPasswordFilled
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
                !email.any { it in 'а'..'я' || it in 'А'..'Я' }
    }
}

sealed class LoginNavigationEvent {
    data object ToMainScreen : LoginNavigationEvent()
}
