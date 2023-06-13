package com.cs23_ps276.sahabatlansia.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/register")
    fun register(@Body request: RegisterRequest): Call<RegisterResponse>

    @POST("/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST("/")
    fun getRecommendedArticles(@Body request: ArticleRequest): Call<ArticleResponse>
}
