package com.example.nit3213project

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @POST("/footscray/auth")  // Adjust this endpoint based on your location
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @GET("/dashboard/{keypass}")
    fun getDashboardData(@Path("keypass") keypass: String): Call<DashboardResponse>
}
