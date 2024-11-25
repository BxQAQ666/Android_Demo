package com.example.demo1.profile

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.ui.platform.LocalContext

@Composable
fun ProfileScreen(navController: NavHostController) {
    val context = LocalContext.current  // 获取当前的上下文

    var userName by remember { mutableStateOf("Alice") }
    var email by remember { mutableStateOf("alice@example.com") }
    var phoneNumber by remember { mutableStateOf("+1234567890") }
    var userBio by remember { mutableStateOf("这是一个个人简介。") }
    var avatarImage by remember { mutableStateOf(android.R.drawable.ic_menu_camera) }  // 默认头像图标

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 头像部分
        Box(
            modifier = Modifier
                .size(120.dp)
                .background(Color.Gray, shape = CircleShape)
        ) {
            Image(
                painter = painterResource(id = avatarImage), // 使用用户上传的头像
                contentDescription = "User Avatar",
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 头像上传按钮
        Button(
            onClick = { /* 打开头像上传功能 */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "更换头像")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 用户名
        Text(
            text = userName,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 邮箱
        Text(
            text = email,
            fontSize = 16.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 电话号码
        Text(
            text = phoneNumber,
            fontSize = 16.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 用户简介
        Text(
            text = userBio,
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 修改资料按钮
        Button(
            onClick = {
                navController.navigate("editProfile")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "编辑资料")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // 修改密码按钮
        OutlinedButton(
            onClick = { navController.navigate("changePassword") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "修改密码")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // 退出登录按钮
        Button(
            onClick = {
                // 清除用户的登录信息
                clearUserSession(context)

                // 跳转到登录界面
                navController.navigate("login") {
                    popUpTo("mainChat") { inclusive = true }  // 退出到登录界面，并清空导航堆栈
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {
            Text(text = "退出登录", color = Color.White)
        }
    }
}

// 清除用户会话信息
fun clearUserSession(context: Context) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.clear()  // 清除所有存储的数据
    editor.apply()
}

// 判断用户是否已登录
fun isUserLoggedIn(context: Context): Boolean {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    return sharedPreferences.getString("user_token", null) != null // 假设是通过 token 判断是否登录
}
