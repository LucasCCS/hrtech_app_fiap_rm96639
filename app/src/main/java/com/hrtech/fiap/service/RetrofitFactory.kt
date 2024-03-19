package com.hrtech.fiap.service

import com.hrtech.fiap.model.JobOpportunity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

    private val URL = "http://192.168.40.213:3000/api/"

    private val retrofitFactory = Retrofit
        .Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getJobOpportunityService(): JobOpportunityService {
        return retrofitFactory.create(JobOpportunityService::class.java)
    }

}