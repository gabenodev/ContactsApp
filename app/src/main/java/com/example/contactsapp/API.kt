package com.example.contactsapp
import retrofit2.http.GET
import retrofit2.Call

interface API {

    @GET("/public/v2/users")
    fun getUser(): Call<MutableList<User>>

}