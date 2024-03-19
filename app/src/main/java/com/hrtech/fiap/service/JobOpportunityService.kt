package com.hrtech.fiap.service

import com.hrtech.fiap.model.JobOpportunity
import retrofit2.Call
import retrofit2.http.GET

interface JobOpportunityService {
    @GET("/job")
    fun getJobOpportunity(): Call<List<JobOpportunity>>
}