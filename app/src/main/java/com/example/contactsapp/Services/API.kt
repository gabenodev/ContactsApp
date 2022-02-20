package com.example.contactsapp.Services
import com.example.contactsapp.Model.Post
import com.example.contactsapp.Model.User
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Path
import retrofit2.http.Query

interface API {

    @GET("/public/v2/users")
    fun getUser(): Call<MutableList<User>>

    @GET("/public/v2/users/{userId}/posts")
    fun getPost(@Path("userId") user_id: Int): Call<MutableList<Post>>

}