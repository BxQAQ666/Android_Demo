package com.example.demo1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.demo1.auth.LoginScreen
import com.example.demo1.auth.RegisterScreen
import com.example.demo1.chat.MainChatScreen
import com.example.demo1.friends.AddFriendScreen
import com.example.demo1.friends.FriendRequestScreen
import com.example.demo1.network.MyWebSocketClient
import com.example.demo1.profile.ChangePasswordScreen
import com.example.demo1.profile.EditProfileScreen
import com.example.demo1.profile.ProfileScreen
import kotlinx.coroutines.launch
import org.java_websocket.client.WebSocketClient
import java.net.URI

class MainActivity : ComponentActivity() {

    private lateinit var webSocketClient: MyWebSocketClient
    private val receivedMessage = mutableStateOf("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeWebSocket()

        lifecycleScope.launch {
            webSocketClient.messages.collect { message ->
                receivedMessage.value = message // 更新收到的消息
            }
        }

        setContent {
            MyApp(receivedMessage)
        }
    }

    private fun initializeWebSocket() {
        val serverUri = URI("ws://192.168.x.x:8080/websocket") // 替换为服务器地址
        webSocketClient = MyWebSocketClient(serverUri)
        webSocketClient.connect()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::webSocketClient.isInitialized && webSocketClient.isOpen) {
            webSocketClient.close()
        }
    }
}

@Composable
fun MyApp(receivedMessage: androidx.compose.runtime.State<String>) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("mainChat") { MainChatScreen(navController) }
        composable("addFriend") { AddFriendScreen(navController) }
        composable("acceptFriendRequest") { FriendRequestScreen(navController) }
        composable("profile") { ProfileScreen(navController) }
        composable("changePassword") { ChangePasswordScreen(navController) }
    }
}
