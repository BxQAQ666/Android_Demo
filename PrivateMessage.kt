package com.example.demo1.pojo

import java.time.LocalDateTime

data class PrivateMessage(
    val messageId: Long,
    val sender: User,
    val receiver: User,
    val messageType: Int,
    val content: String?,
    val attachmentUrl: String?,
    val timestamp: LocalDateTime,
    val status: Int,
    val deleted: Boolean
)