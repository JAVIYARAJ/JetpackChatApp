package com.example.jetpackdesign.data.model

data class ProfileItemModel(
    val label: String,
    val title: String,
    val isLink: Boolean=false,
    val isLast: Boolean = false
)
