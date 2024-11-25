package com.example.demo1.friends

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FriendRequestPage() {
    // 示例数据
    var friendRequests by remember { mutableStateOf(
        listOf(
            FriendRequest("Alice", "10:30 AM"),
            FriendRequest("Bob", "11:00 AM"),
            FriendRequest("Charlie", "11:15 AM")
        )
    )}
    var friends by remember { mutableStateOf(listOf<Friend>()) }

    // 处理接受请求
    fun acceptRequest(request: FriendRequest) {
        // 将请求者添加到好友列表
        friends = friends + Friend(request.userName)
        // 移除请求
        friendRequests = friendRequests.filter { it != request }
    }

    // 处理拒绝请求
    fun rejectRequest(request: FriendRequest) {
        // 从请求列表中移除
        friendRequests = friendRequests.filter { it != request }
    }

    // 显示好友请求页面
    //FriendRequestScreen(navController: NavHostController)

    // 可选择显示已接受的好友列表
    if (friends.isNotEmpty()) {
        Text(text = "已添加的好友:", fontSize = 20.sp, modifier = Modifier.padding(top = 16.dp))
        LazyColumn(modifier = Modifier.padding(top = 8.dp)) {
            items(friends) { friend ->
                Text(text = friend.userName, fontSize = 16.sp)
            }
        }
    }
}
