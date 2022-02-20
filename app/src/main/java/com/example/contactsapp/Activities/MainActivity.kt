package com.example.contactsapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapp.Adapter.UserAdapter
import com.example.contactsapp.Model.User
import com.example.contactsapp.Services.API
import com.example.contactsapp.Services.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

     lateinit var userList: MutableList<User>
     private lateinit var recyclerView: RecyclerView
     private lateinit var manager: RecyclerView.LayoutManager
     private lateinit var myAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val serviceGenerator = RetrofitService.buildService(API::class.java)
        val call = serviceGenerator.getUser()
        manager = LinearLayoutManager(this)
        val recyclerView = findViewById<RecyclerView>(R.id.contactsRV)
        val userListActive = mutableListOf<User>()

        val avatarList = mutableListOf<Int>(R.drawable.profile_icon,R.drawable.profile_icon2,R.drawable.profile_icon3,R.drawable.profile_icon4,R.drawable.profile_icon5,R.drawable.initials_bg)


        // Getting response from the API
        call.enqueue(object:Callback<MutableList<User>>{
            override fun onResponse(
                call: Call<MutableList<User>>,
                response: Response<MutableList<User>>
            ) {
                if(response.isSuccessful){
                    userList = response.body() as MutableList<User>
                    for(i in 0 until userList.size){
                        if(userList.get(i).status.equals("active")) {
                            userListActive.add(userList.get(i))
                        }
                    }

                    for(i in 0 until userListActive.size){
                        if(userListActive[i].id!! % 2 == 1)
                        userListActive[i].image = avatarList[Random.nextInt(0,avatarList.size-1)]
                    }

                    Log.e("MESSAGE", "THE LAST PERSON ACTIVE IN API IS... " + userListActive.get(userListActive.size-1).toString())
                        recyclerView.apply {

                            layoutManager = manager
                           myAdapter = UserAdapter(userListActive)
                           //Log.e("MESSAGE2" ,"UserAdapter has... items "+  UserAdapter(userListActive).itemCount )
                            adapter = myAdapter

                    }

                myAdapter.setOnItemClickListener(object : UserAdapter.onItemClickListener{
                    override fun onItemClick(index: Int) {

                        Toast.makeText(this@MainActivity,"You clicked the user with the index $index " , Toast.LENGTH_LONG).show()
                        intent = Intent(this@MainActivity, UserProfileActivity::class.java)
                        intent.putExtra("name",userListActive[index].name)
                        intent.putExtra("email",userListActive[index].email)
                        intent.putExtra("image",userListActive[index].image)
                        intent.putExtra("id",userListActive[index].id)
                        startActivity(intent)

                    }

                })

                }
            }

            override fun onFailure(call: Call<MutableList<User>>, t: Throwable) {
                t.printStackTrace()
                Log.e("error" , t.message.toString())
            }

        })




    }

    /*
    fun deleteItem(index: Int){
        if(userList.get(index).status.equals("inactive"))
            userList.removeAt(index)
            myAdapter.setItems(userList)
    }

     */


}