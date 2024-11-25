package com.example.demo1.pojo.dto

import com.example.demo1.pojo.User
import java.time.LocalDateTime

data class UserFriendDto(
    val id: Int?,
    val user: User,  // 参考你的用户类定义
    val friend: User,
    val status: Int,
    val nickname: String?,
    val isBlocked: Boolean = false,
    val createdAt: LocalDateTime = LocalDateTime.now()
)