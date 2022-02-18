package com.example.contactsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapp.Adapter.UserAdapter
import com.example.contactsapp.Model.User
import com.example.contactsapp.Services.API
import com.example.contactsapp.Services.Service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val serviceGenerator = Service.buildService(API::class.java)
        val call = serviceGenerator.getUser()

        val recyclerView = findViewById<RecyclerView>(R.id.contactsRV)

        // Getting response from the API
        call.enqueue(object:Callback<MutableList<User>>{
            override fun onResponse(
                call: Call<MutableList<User>>,
                response: Response<MutableList<User>>
            ) {
                if(response.isSuccessful){
                    recyclerView.apply{
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = UserAdapter(response.body()!!)
                    }

                    //Log.e("success", response.body().toString())
                }
            }

            override fun onFailure(call: Call<MutableList<User>>, t: Throwable) {
                t.printStackTrace()
                Log.e("error" , t.message.toString())
            }

        })


    }
}