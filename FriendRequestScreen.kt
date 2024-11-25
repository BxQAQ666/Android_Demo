package com.example.demo1.friends

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

// 数据模型
data class FriendRequest(val userName: String, val requestTime: String)
data class Friend(val userName: String)

@Composable
fun FriendRequestScreen(navController: NavHostController) {
    // 这里是模拟数据，可以由父级传递过来或在这里初始化
    val friendRequests = remember {
        mutableStateOf(
            listOf(
                FriendRequest("Alice", "12:30 PM"),
                FriendRequest("Bob", "1:45 PM"),
                FriendRequest("Charlie", "2:00 PM")
            )
        )
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "好友申请", fontSize = 20.sp, modifier = Modifier.padding(bottom = 16.dp))

        // 显示好友请求列表
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(friendRequests.value) { request ->
                FriendRequestItem(
                    friendRequest = request,
                    onAcceptRequest = {
                        // 处理接受请求
                        friendRequests.value = friendRequests.value.filter { it != request }
                    },
                    onRejectRequest = {
                        // 处理拒绝请求
                        friendRequests.value = friendRequests.value.filter { it != request }
                    }
                )
            }
        }
    }
}

@Composable
fun FriendRequestItem(
    friendRequest: FriendRequest,
    onAcceptRequest: () -> Unit,
    onRejectRequest: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.size(50.dp).background(Color.Gray, shape = CircleShape))
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = friendRequest.userName, fontSize = 16.sp)
            Text(text = friendRequest.requestTime, fontSize = 12.sp, color = Color.Gray)
        }
        Spacer(modifier = Modifier.width(8.dp))
        Row {
            Button(onClick = onAcceptRequest) { Text("接受") }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = onRejectRequest) { Text("拒绝") }
        }
    }
}
