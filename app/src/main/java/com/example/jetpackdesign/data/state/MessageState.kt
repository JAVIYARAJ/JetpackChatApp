package com.example.jetpackdesign.data.state

import androidx.compose.runtime.toMutableStateList
import com.example.jetpackdesign.data.model.message.Message

class MessageState(initialMessages: List<Message>) {

    private val _messages = initialMessages.toMutableStateList()
    val messages: List<Message> = _messages

    fun addMessages(msg: Message) {
        _messages.add(msg)
    }

}