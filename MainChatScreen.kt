package com.example.demo1

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import kotlinx.coroutines.launch

data class ChatSession(val userName: String, val lastMessage: String, val timestamp: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainChatScreen(navController: NavHostController) {
    val chatSessions = listOf(
        ChatSession("Alice", "Hey, how are you?", "12:34 PM"),
        ChatSession("Bob", "Let's catch up soon!", "11:15 AM"),
        ChatSession("Charlie", "Meeting at 3 PM today", "10:45 AM")
    )

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("聊天列表", fontSize = 18.sp) },
                actions = {
                    IconButton(onClick = {
                        coroutineScope.launch {
                            drawerState.open()
                        }
                    }) {
                        Icon(Icons.Filled.Person, contentDescription = "User Profile")
                    }
                }
            )
        },
        //drawer = {
            // 好友列表
            //FriendListDrawer(chatSessions)
        //},
        //drawerState = drawerState
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // 聊天列表
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(chatSessions) { chatSession ->
                    ChatItem(chatSession, navController)
                }
            }
        }
    }
}

@Composable
fun FriendListDrawer(chatSessions: List<ChatSession>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("好友列表", fontSize = 20.sp)

        Spacer(modifier = Modifier.height(16.dp))

        // 显示好友列表
        LazyColumn {
            items(chatSessions) { chatSession ->
                Text(
                    text = chatSession.userName,
                    //style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 添加好友按钮
        Button(
            onClick = { /* 这里处理添加好友的操作，例如打开添加好友页面 */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("添加好友")
        }
    }
}

@Composable
fun ChatItem(chatSession: ChatSession, navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(70.dp)
            .clickable {
                // 跳转到聊天详情页面
                // Example: navController.navigate("chat/${chatSession.userName}")
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
        Text(text = chatSession.timestamp, fontSize = 12.sp, color = Color.Gray, modifier = Modifier.align(Alignment.CenterVertically))
    }
}
