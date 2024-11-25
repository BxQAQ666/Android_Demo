package com.example.demo1.pojo

import java.time.LocalDateTime

data class UserFriend(
    val id: Int,
    val user: User,
    val friend: User,
    val status: Int,
    val nickname: String?,
    val isBlocked: Boolean,
    val createdAt: LocalDateTime
)