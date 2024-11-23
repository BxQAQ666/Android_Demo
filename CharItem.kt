package com.example.demo1.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

data class ChatSession(val userName: String, val lastMessage: String, val timestamp: String)

@Composable
fun ChatItem(chatSession: ChatSession, navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(70.dp)
            .clickable {
                // 可跳转到聊天详情页面
                navController.navigate("chatDetail/${chatSession.userName}")
            }
    ) {
        // 用户头像
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(Color.Gray, shape = CircleShape)
                .align(Alignment.CenterVertically)
        )

        Spacer(modifier = Modifier.width(8.dp))

        // 聊天内容
        Column(modifier = Modifier.align(Alignment.CenterVertically)) {
            Text(text = chatSession.userName, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = chatSession.lastMessage, fontSize = 14.sp, color = Color.Gray)
        }

        Spacer(modifier = Modifier.weight(1f))

        // 时间戳
        Text(
            text = chatSession.timestamp,
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}
