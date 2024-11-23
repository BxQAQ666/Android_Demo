package com.example.demo1.friends

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FriendListScreen(friendList: List<String>, onAddFriend: (String) -> Unit) {
    var newFriendName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "好友列表",
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // 好友列表
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            items(friendList) { friend ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .background(Color.Gray, shape = CircleShape)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = friend,
                        fontSize = 16.sp,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 添加好友输入框和按钮
        Row {
            TextField(
                value = newFriendName,
                onValueChange = { newFriendName = it },
                placeholder = { Text("输入好友名称") },
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {
                    if (newFriendName.isNotBlank()) {
                        onAddFriend(newFriendName.trim())
                        newFriendName = ""
                    }
                },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text("添加")
            }
        }
    }
}
