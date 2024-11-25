package com.example.demo1.profile

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.ui.platform.LocalContext

@Composable
fun ChangePasswordScreen(navController: NavHostController) {
    val context = LocalContext.current // 获取Context，用于显示Toast
    var currentPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isCurrentPasswordValid by remember { mutableStateOf(false) } // 是否输入正确的当前密码
    var isPasswordMatch by remember { mutableStateOf(true) } // 新密码和确认密码是否匹配

    // 模拟一个正确的当前密码（在实际应用中，这个密码应该通过服务器验证）
    val correctCurrentPassword = "123456"  // 假设当前密码是"123456"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 当前密码输入框
        PasswordInputField(
            label = "当前密码",
            value = currentPassword,
            onValueChange = { currentPassword = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 提交按钮，用于验证当前密码是否正确
        Button(
            onClick = {
                // 当用户点击提交按钮时才进行验证
                isCurrentPasswordValid = currentPassword == correctCurrentPassword
                if (!isCurrentPasswordValid) {
                    Toast.makeText(context, "密码错误", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "验证当前密码")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 如果当前密码正确，允许输入新密码
        if (isCurrentPasswordValid) {
            // 新密码输入框
            PasswordInputField(
                label = "新密码",
                value = newPassword,
                onValueChange = { newPassword = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 确认新密码输入框
            PasswordInputField(
                label = "确认新密码",
                value = confirmPassword,
                onValueChange = { confirmPassword = it }
            )

            // 判断新密码和确认密码是否匹配
            isPasswordMatch = newPassword == confirmPassword

            Spacer(modifier = Modifier.height(24.dp))

            // 提交按钮
            Button(
                onClick = {
                    if (isPasswordMatch) {
                        // 处理密码修改的逻辑
                        Toast.makeText(context, "密码修改成功", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "新密码和确认密码不匹配", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = isPasswordMatch
            ) {
                Text(text = "提交修改")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 取消按钮
        OutlinedButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "取消")
        }
    }
}

@Composable
fun PasswordInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        visualTransformation = PasswordVisualTransformation(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.LightGray)
            .padding(16.dp),
        decorationBox = { innerTextField ->
            Box(modifier = Modifier.fillMaxWidth()) {
                if (value.isEmpty()) {
                    Text(text = label, color = Color.Gray, fontSize = 16.sp)
                }
                innerTextField()
            }
        }
    )
}
