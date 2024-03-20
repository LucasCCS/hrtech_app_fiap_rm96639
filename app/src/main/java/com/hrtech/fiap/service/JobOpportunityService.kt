package com.hrtech.fiap.service

import com.hrtech.fiap.model.JobOpportunity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface JobOpportunityService {
    @GET("/job")
    fun getJobOpportunity(@Query("search") search: String): Call<List<JobOpportunity>>
}