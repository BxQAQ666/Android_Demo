package com.example.demo1.friends

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFriendScreen(navController: NavHostController) {
    var friendUsername by remember { mutableStateOf("") }
    var isRequestSent by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("添加好友") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                TextField(
                    value = friendUsername,
                    onValueChange = { friendUsername = it },
                    label = { Text("请输入用户名或邮箱") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        // 这里可以实现添加好友的逻辑
                        isRequestSent = true
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("发送好友请求")
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (isRequestSent) {
                    Text("好友请求已发送", color = Color.Green)
                }
            }
        }
    )
}
