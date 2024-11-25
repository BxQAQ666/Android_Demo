package com.example.demo1.pojo.dto

import com.example.demo1.pojo.User
import java.time.LocalDateTime
import java.time.LocalDateTime.now

data class PrivateMessageDto(
    val messageId: Long?,
    val sender: User,  // 参考你的用户类定义
    val receiver: User,
    val messageType: Int,
    val content: String?,
    val attachmentUrl: String?,
    val timestamp: LocalDateTime = now(),
    val status: Int,
    val deleted: Boolean
)