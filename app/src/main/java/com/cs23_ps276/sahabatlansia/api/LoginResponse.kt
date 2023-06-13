package com.cs23_ps276.sahabatlansia.api

data class LoginResponse(
    val statusCode: Int,
    val message: String,
    val data: ErrorField?
)

