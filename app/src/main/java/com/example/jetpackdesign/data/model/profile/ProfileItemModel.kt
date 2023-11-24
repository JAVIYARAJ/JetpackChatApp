package com.example.jetpackdesign.data.model.profile

data class ProfileItemModel(
    val label: String,
    var title: String,
    val isLink: Boolean=false,
    val isLast: Boolean = false
)
