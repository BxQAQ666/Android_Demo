package com.example.demo1.chat

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.demo1.friends.FriendListScreen
import com.example.demo1.profile.ProfileScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainChatScreen(navController: NavHostController) {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("主页", "好友", "个人信息")  // 这里将 '设置' 改为 '个人信息'
    val chatSessions = listOf(
        ChatSession("Alice", "Hey, how are you?", "12:34 PM"),
        ChatSession("Bob", "Let's catch up soon!", "11:15 AM"),
        ChatSession("Charlie", "Meeting at 3 PM today", "10:45 AM")
    )

    // 好友列表的状态
    val friendList = remember { mutableStateOf(listOf("David", "Emily", "Frank")) }

    Scaffold(
        topBar = {
            when (selectedItem) {
                0 -> TopAppBar(title = { Text("聊天列表", fontSize = 18.sp) })
                1 -> {
                    // 好友列表的 TopAppBar，增加添加好友按钮
                    TopAppBar(
                        title = { Text("好友列表", fontSize = 18.sp) },
                        actions = {
                            // 添加好友按钮
                            IconButton(onClick = {
                                // 跳转到添加好友界面
                                navController.navigate("addFriend")
                            }) {
                                Icon(Icons.Filled.Add, contentDescription = "Add Friend")
                            }

                            // 接受好友申请按钮
                            IconButton(onClick = {
                                // 跳转到接受好友申请界面
                                navController.navigate("acceptFriendRequest")
                            }) {
                                Icon(Icons.Filled.Person, contentDescription = "Accept Friend Request")
                            }
                        }
                    )
                }
                2 -> {
                    // 个人信息页面的 TopAppBar
                    TopAppBar(title = { Text("个人信息", fontSize = 18.sp) })
                }
            }
        },
        bottomBar = {
            BottomNavigation {
                items.forEachIndexed { index, item ->
                    BottomNavigationItem(
                        icon = {
                            when (index) {
                                0 -> Icon(Icons.Filled.Notifications, contentDescription = null)
                                1 -> Icon(Icons.Filled.Person, contentDescription = null)
                                else -> Icon(Icons.Filled.Info, contentDescription = null)
                            }
                        },
                        label = { Text(item) },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index }
                    )
                }
            }
        }
    ) { innerPadding -> // 使用innerPadding来处理顶部的内容
        when (selectedItem) {
            0 -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding) // 在这里使用innerPadding，避免内容被TopAppBar遮挡
                ) {
                    items(chatSessions) { chatSession ->
                        ChatItem(chatSession, navController)
                    }
                }
            }
            1 -> {
                FriendListScreen(
                    friendList = friendList.value,
                    onAddFriend = { newFriend ->
                        friendList.value += newFriend
                    }
                )
            }
            2 -> {
                // 显示个人信息的页面内容，使用内边距避免TopAppBar遮挡内容
                ProfileScreen(navController)
            }
        }
    }
}

