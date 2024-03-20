package com.hrtech.fiap.service;


import com.hrtech.fiap.model.AuthenticationResponse
import com.hrtech.fiap.model.Credentials
import com.hrtech.fiap.model.SignupData
import retrofit2.Call

import retrofit2.http.Body;
import retrofit2.http.POST;

interface AuthService {
    @POST("/auth/login")
    fun login(@Body credentials: Credentials): Call<AuthenticationResponse>

    @POST("/auth/signup")
    fun signup(@Body credentials: Credentials): Call<AuthenticationResponse>
}
