package com.hrtech.fiap.model


data class AuthenticationResponse(val accessToken: String)
data class Credentials(val username: String, val password: String)
data class SignupData(val username: String, val password: String)

