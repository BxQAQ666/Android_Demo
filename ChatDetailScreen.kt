package com.example.demo1.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatDetailScreen(userName: String) {
    var message by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(" $userName ") },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back navigation */ }) {
                        Icon(Icons.Filled.Close, contentDescription = "Back")
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
                // 显示聊天记录
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = " $userName ", fontSize = 16.sp)

                Spacer(modifier = Modifier.weight(1f))

                // 输入框和发送按钮
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BasicTextField(
                        value = message,
                        onValueChange = { message = it },
                        modifier = Modifier
                            .weight(1f)
                            .background(Color.LightGray, shape = MaterialTheme.shapes.medium)
                            .padding(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    IconButton(onClick = { /* Send message */ }) {
                        Icon(Icons.AutoMirrored.Filled.Send, contentDescription = "Send Message")
                    }
                }
            }
        }
    )
}
