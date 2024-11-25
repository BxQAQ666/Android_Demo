package com.example.demo1.network

import android.util.Log
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.net.URI

class MyWebSocketClient(serverUri: URI) : WebSocketClient(serverUri) {

    private val _messages = MutableSharedFlow<String>()
    val messages: SharedFlow<String> = _messages

    override fun onOpen(handshakedata: ServerHandshake?) {
        Log.d("WebSocket", "Connected to server")
    }

    override fun onMessage(message: String?) {
        message?.let {
            Log.d("WebSocket", "Message received: $it")
            _messages.tryEmit(it) // 发送消息到 Flow
        }
    }

    override fun onClose(code: Int, reason: String?, remote: Boolean) {
        Log.d("WebSocket", "Connection closed: $reason")
    }

    override fun onError(ex: Exception?) {
        Log.e("WebSocket", "Error occurred: ${ex?.message}")
    }

    fun sendMessage(message: String) {
        if (this.isOpen) {
            this.send(message)
            Log.d("WebSocket", "Message sent: $message")
        } else {
            Log.w("WebSocket", "WebSocket is not open. Message not sent: $message")
        }
    }
}
