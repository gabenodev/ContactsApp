package com.example.contactsapp.Services
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Service {
    private val server = OkHttpClient.Builder().build();

    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl("https://gorest.co.in")
        .addConverterFactory(GsonConverterFactory.create())
        .client(server)
        .build()

    fun <T> buildService(service: Class<T>): T {
        return retrofitBuilder.create(service);
    }


}