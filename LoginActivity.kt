package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnRegister: Button
    private lateinit var tvForgotPassword: TextView
    private lateinit var tvMessage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_layout)

        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        btnRegister = findViewById(R.id.btnRegister)
        tvForgotPassword = findViewById(R.id.tvForgotPassword)
        tvMessage = findViewById(R.id.tvMessage)

        btnLogin.setOnClickListener {
            handleLogin()
        }

        btnRegister.setOnClickListener {
            handleRegister()
        }

        tvForgotPassword.setOnClickListener {
            // Show forgot password logic
            Toast.makeText(this, "Forgot Password clicked", Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleLogin() {
        val username = etUsername.text.toString()
        val password = etPassword.text.toString()

        if (username.isNotEmpty() && password.isNotEmpty()) {
            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
        } else {
            tvMessage.text = "Please enter both username and password"
        }
    }

    private fun handleRegister() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }
}
