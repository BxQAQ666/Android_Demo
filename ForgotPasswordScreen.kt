import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.ui.platform.LocalContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var verificationCode by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var isCodeSent by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current  // 获取Context

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("忘记密码", fontSize = 18.sp) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // 用户邮箱输入框
            if (!isCodeSent) {
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("请输入注册时的邮箱") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 错误提示
                errorMessage?.let {
                    Text(
                        text = it,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

                // 发送验证码按钮
                Button(
                    onClick = {
                        // 发送验证码逻辑
                        sendVerificationCode(email, context)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("发送验证码")
                }
            } else {
                // 用户输入验证码和新密码
                TextField(
                    value = verificationCode,
                    onValueChange = { verificationCode = it },
                    label = { Text("请输入验证码") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = newPassword,
                    onValueChange = { newPassword = it },
                    label = { Text("请输入新密码") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 重置密码按钮
                Button(
                    onClick = {
                        // 重置密码逻辑
                        resetPassword(email, verificationCode, newPassword, context)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("重置密码")
                }
            }
        }
    }
}

fun sendVerificationCode(email: String, context: android.content.Context) {
    if (email.isEmpty()) {
        Toast.makeText(context, "邮箱不能为空", Toast.LENGTH_SHORT).show()
        return
    }

    // 检查邮箱格式
    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        Toast.makeText(context, "请输入有效的邮箱地址", Toast.LENGTH_SHORT).show()
        return
    }

    // 使用 Firebase 发送验证码邮件
    FirebaseAuth.getInstance().sendPasswordResetEmail(email)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // 验证码已发送
                Toast.makeText(context, "验证码已发送，请检查您的邮箱", Toast.LENGTH_SHORT).show()
            } else {
                // 发送失败
                task.exception?.let {
                    Toast.makeText(context, "发送验证码失败: ${it.message}", Toast.LENGTH_SHORT).show()
                } ?: run {
                    Toast.makeText(context, "发生未知错误，请稍后再试", Toast.LENGTH_SHORT).show()
                }
            }
        }
}


fun resetPassword(email: String, verificationCode: String, newPassword: String, context: android.content.Context) {
    // 验证验证码并重置密码
    val user = FirebaseAuth.getInstance().currentUser
    if (user != null && verificationCode == "123456") {  // 替换为实际验证码验证逻辑
        user.updatePassword(newPassword)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(context, "密码已重置，请重新登录", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "重置密码失败: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    } else {
        Toast.makeText(context, "验证码不正确", Toast.LENGTH_SHORT).show()
    }
}
