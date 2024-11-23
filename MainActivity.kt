package com.example.demo1

import ForgotPasswordScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.demo1.auth.LoginScreen
import com.example.demo1.auth.RegisterScreen
import com.example.demo1.chat.ChatDetailScreen
import com.example.demo1.chat.MainChatScreen
import com.example.demo1.friends.AddFriendScreen
import com.example.demo1.friends.FriendRequestScreen
import com.example.demo1.profile.ChangePasswordScreen
import com.example.demo1.profile.EditProfileScreen
import com.example.demo1.profile.ProfileScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("forgotPassword") { ForgotPasswordScreen(navController) }
        composable("mainChat") { MainChatScreen(navController) }
        //composable("chatSession") { ChatDetailScreen("peter") }
        composable("addFriend") { AddFriendScreen(navController) }
        composable("acceptFriendRequest") { FriendRequestScreen(navController) }
        composable("profile") { ProfileScreen(navController) }
        composable("changePassword") { ChangePasswordScreen(navController) }
        //navController.navigate("editProfile/$userName/$userBio")
        //composable("charDetail") { ChatDetailScreen("peter") }
    }
}
