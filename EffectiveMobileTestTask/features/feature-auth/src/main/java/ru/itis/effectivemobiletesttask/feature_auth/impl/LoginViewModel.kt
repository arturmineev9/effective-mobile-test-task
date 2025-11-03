package ru.itis.effectivemobiletesttask.feature_auth.impl

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel : ViewModel() {

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _isLoginEnabled = MutableStateFlow(false)
    val isLoginEnabled: StateFlow<Boolean> = _isLoginEnabled

    fun onEmailChanged(email: String) {
        _email.value = email
        validateFields()
    }

    fun onPasswordChanged(password: String) {
        _password.value = password
        validateFields()
    }

    fun onLoginClicked() {
        // TODO UseCase
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
