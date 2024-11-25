package com.example.demo1

import com.example.demo1.pojo.PrivateMessage
import com.example.demo1.pojo.ResponseMessage
import com.example.demo1.pojo.User
import com.example.demo1.pojo.UserFriend
import com.example.demo1.pojo.dto.PrivateMessageDto
import com.example.demo1.pojo.dto.UserDto
import com.example.demo1.pojo.dto.UserFriendDto
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    // User APIs
    @POST("/user")
    fun addUser(@Body user: UserDto): Call<ResponseMessage<User>>

    @DELETE("/user/{userId}")
    fun deleteUser(@Path("userId") userId: Int): Call<ResponseMessage<Void>>

    @GET("/user/{userId}")
    fun getUser(@Path("userId") userId: Int): Call<ResponseMessage<User>>

    @PUT("/user")
    fun editUser(@Body user: UserDto): Call<ResponseMessage<User>>

    // UserFriend APIs
    @POST("/userFriend")
    fun addUserFriend(@Body userFriend: UserFriendDto): Call<ResponseMessage<UserFriend>>

    @DELETE("/userFriend/{userId}/{friendId}")
    fun deleteUserFriend(@Path("userId") userId: Int, @Path("friendId") friendId: Int): Call<ResponseMessage<Void>>

    @GET("/userFriend/{userId}/{friendId}")
    fun getUserFriend(@Path("userId") userId: Int, @Path("friendId") friendId: Int): Call<ResponseMessage<UserFriend>>

    @PUT("/userFriend")
    fun editUserFriend(@Body userFriend: UserFriendDto): Call<ResponseMessage<UserFriend>>

    // PrivateMessage APIs
    @POST("/private-messages")
    fun addPrivateMessage(@Body privateMessageDto: PrivateMessageDto): Call<ResponseMessage<PrivateMessage>>

    @GET("/private-messages/{senderId}/{receiverId}")
    fun getPrivateMessages(
        @Path("senderId") senderId: Int,
        @Path("receiverId") receiverId: Int,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Call<List<PrivateMessage>>

    @GET("/private-messages/{senderId}/{receiverId}/latest")
    fun getLatestMessage(@Path("senderId") senderId: Int, @Path("receiverId") receiverId: Int): Call<ResponseMessage<PrivateMessage>>

    @DELETE("/private-messages/{messageId}")
    fun deletePrivateMessage(@Path("messageId") messageId: Long): Call<ResponseMessage<Void>>

    @PUT("/private-messages/{messageId}/recall")
    fun recallMessage(@Path("messageId") messageId: Long): Call<ResponseMessage<Void>>

    @GET("/private-messages/{userId}/unread-count")
    fun getUnreadMessageCount(@Path("userId") userId: Int): Call<ResponseMessage<Long>>

    @DELETE("/private-messages/user/{userId}")
    fun deleteMessagesByUserId(@Path("userId") userId: Int): Call<ResponseMessage<Void>>
}
