package com.example.demo1.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EditProfileScreen(
    userName: String,
    userBio: String,
    onSave: (String, String) -> Unit, // 保存回调
    onCancel: () -> Unit // 取消回调
) {
    // 用传入的值初始化
    var modifiedUserName by remember { mutableStateOf(userName) }
    var modifiedUserBio by remember { mutableStateOf(userBio) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // 标题
        Text(text = "编辑资料", fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))

        // 用户名输入框
        Text("用户名", fontSize = 16.sp)
        BasicTextField(
            value = modifiedUserName,
            onValueChange = { modifiedUserName = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .background(Color.Gray.copy(alpha = 0.1f), shape = MaterialTheme.shapes.small)
                .padding(16.dp)
        )

        // 个人简介输入框
        Text("个人简介", fontSize = 16.sp)
        BasicTextField(
            value = modifiedUserBio,
            onValueChange = { modifiedUserBio = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .background(Color.Gray.copy(alpha = 0.1f), shape = MaterialTheme.shapes.small)
                .padding(16.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { /* Handle save or close */ }
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 保存按钮
        Button(
            onClick = {
                onSave(modifiedUserName, modifiedUserBio) // 保存修改
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "保存修改")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // 取消按钮
        OutlinedButton(
            onClick = { onCancel() }, // 取消修改
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "取消")
        }
    }
}
