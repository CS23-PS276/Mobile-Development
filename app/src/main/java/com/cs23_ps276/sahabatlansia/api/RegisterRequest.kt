package com.cs23_ps276.sahabatlansia.api

data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String,
    val retypePassword: String
)
