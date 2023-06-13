package com.cs23_ps276.sahabatlansia.api

data class RegisterResponse(
    val statusCode: Int,
    val message: String,
    val data: ErrorField?
)

data class ErrorField(
    val type: String,
    val message: String,
    val field: String
)
