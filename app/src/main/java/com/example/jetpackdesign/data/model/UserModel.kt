package com.example.jetpackdesign.data.model

data class UserModel(
    val id: String,
    val name: String,
    val email: String,
    val description: String,
    val image: Int,
    val status: String = "Active",
    val country: String = "India",
    val language: String = "English"
)