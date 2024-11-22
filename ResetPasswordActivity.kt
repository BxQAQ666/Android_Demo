package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResetPasswordActivity  : AppCompatActivity(){
    private lateinit var tvResetMsg : TextView
    private lateinit var etEmail : EditText
    private lateinit var btnVerification : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.resetpassword_email_layout)

        tvResetMsg = findViewById(R.id.tvResetMsg)
        etEmail = findViewById(R.id.etEmail)
        btnVerification = findViewById(R.id.btnVerification)

        btnVerification.setOnClickListener{
            //--创建验证码并发送验证码并发送（未完成）
            showVerificationDialog()
        }
    }

    // 弹出输入验证码的对话框
    private fun showVerificationDialog() {
        // 创建对话框布局
        val dialogView = layoutInflater.inflate(R.layout.verification_dialog, null)
        val etVerificationCode = dialogView.findViewById<EditText>(R.id.etVerificationCode)

        // 创建AlertDialog
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Enter Verification Code")
            .setView(dialogView)
            .setPositiveButton("Confirm") { dialog: DialogInterface, which: Int ->
                val verificationCode = etVerificationCode.text.toString()

                // 校验验证码（假设验证码是"1234"）
                if (verificationCode == "1234") {
                    // 如果验证码正确，跳转到重置密码界面
                    Toast.makeText(this, "验证成功", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, ResetPasswordActivity::class.java))
                } else {
                    // 如果验证码错误，提示用户
                    Toast.makeText(this, "验证失败", Toast.LENGTH_SHORT).show()
                }
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog: DialogInterface, which: Int ->
                dialog.dismiss()
            }

        alertDialog.show()
    }


}