package com.example.contactsapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
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

     lateinit var userList: MutableList<User>
     private lateinit var recyclerView: RecyclerView
     private lateinit var manager: RecyclerView.LayoutManager
     private lateinit var myAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val serviceGenerator = Service.buildService(API::class.java)
        val call = serviceGenerator.getUser()
        manager = LinearLayoutManager(this)
        val recyclerView = findViewById<RecyclerView>(R.id.contactsRV)
        val userListActive = mutableListOf<User>()

        // Getting response from the API
        call.enqueue(object:Callback<MutableList<User>>{
            override fun onResponse(
                call: Call<MutableList<User>>,
                response: Response<MutableList<User>>
            ) {
                if(response.isSuccessful){
                    userList = response.body() as MutableList<User>
                    //Log.e("MESSAGE", "THE LAST PERSON IN API IS... " + userList.get(userList.size-1).toString() )

                    for(i in 0 until userList.size){
                        if(userList.get(i).status.equals("active")) {
                            userListActive.add(userList.get(i))
                        }
                    }
                    Log.e("MESSAGE", "THE LAST PERSON ACTIVE IN API IS... " + userListActive.get(userListActive.size-1).toString())
                        recyclerView.apply {

                            layoutManager = manager
                            myAdapter = UserAdapter(userListActive)
                           //Log.e("MESSAGE2" ,"UserAdapter has... items "+  UserAdapter(userListActive).itemCount )
                            adapter = myAdapter


                    }

                }
            }

            override fun onFailure(call: Call<MutableList<User>>, t: Throwable) {
                t.printStackTrace()
                Log.e("error" , t.message.toString())
            }

        })


    }

    fun deleteItem(index: Int){
        if(userList.get(index).status.equals("inactive"))
            userList.removeAt(index)
            myAdapter.setItems(userList)
    }

}