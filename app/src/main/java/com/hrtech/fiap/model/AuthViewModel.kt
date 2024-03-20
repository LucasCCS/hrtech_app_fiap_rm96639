package com.hrtech.fiap.model;

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State

class AuthViewModel : ViewModel() {
    private val _token = mutableStateOf("")
    val token: State<String> = _token

    private val _loggedIn = mutableStateOf(false)
    val loggedIn: State<Boolean> = _loggedIn

    fun setToken(token: String) {
        _token.value = token
        _loggedIn.value = token.isNotEmpty()
    }

    fun clearToken() {
        _token.value = ""
        _loggedIn.value = false
    }
}