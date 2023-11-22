package com.example.jetpackdesign.data.model

data class CallHistoryModel(
    val id: String,
    val user: UserModel,
    val callType: CallType,
    val callFeature: CallFeature,
    val timestamp: String,
)

enum class CallType {
    Cancelled,
    Outgoing,
    Incoming
}

enum class CallFeature{
    Video,
    Audio
}