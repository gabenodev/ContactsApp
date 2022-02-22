package com.example.contactsapp

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapp.Adapter.PostAdapter
import com.example.contactsapp.Model.Post
import com.example.contactsapp.Services.API
import com.example.contactsapp.Services.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserProfileActivity : AppCompatActivity() {

    var userPosts : MutableList<Post>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        val name: TextView = findViewById(R.id.userName)
        val email: TextView = findViewById(R.id.userEmail)
        val profileIcon: ImageView = findViewById(R.id.profileAvatar)
        val firstInitial: TextView = findViewById(R.id.fInitial)
        val secondInitial: TextView = findViewById(R.id.sInitial)
        val userId: Int = intent.getIntExtra("id",1)
        //Log.e("id", "The id of the user given to me is " + id.toString())
        val image: Int = intent.getIntExtra("image",R.drawable.initials_bg)
        val noPosts:TextView = findViewById(R.id.noPosts)
        val backButton: ImageView = findViewById(R.id.backButton)
        //Init the Recycler View
        val recyclerView = findViewById<RecyclerView>(R.id.postsRV)

        //Setting up Retrofit
        val serviceGenerator = RetrofitService.buildService(API::class.java)
        Log.e("ID", userId.toString())
        val call = serviceGenerator.getPost(userId)

        call.enqueue(object: Callback<MutableList<Post>>{
            override fun onResponse(
                call: Call<MutableList<Post>>,
                response: Response<MutableList<Post>>
            ) {
                if(response.isSuccessful){
                    userPosts = response.body() as MutableList<Post>

                    if(userPosts!!.size > 0){
                    recyclerView.apply{
                        layoutManager = LinearLayoutManager(this@UserProfileActivity)
                        adapter = PostAdapter(userPosts!!)
                    }
                    } else {
                        noPosts.isVisible = true
                    }
                }
            }

            override fun onFailure(call: Call<MutableList<Post>>, t: Throwable) {
                t.printStackTrace()
                Log.e("error" , t.message.toString())
            }

        })

        Log.e("DEBUG MESSAG", "THIS PERSON HAS..." + (userPosts?.size))


        name.text = intent.getStringExtra("name")
        email.text = intent.getStringExtra("email")
        profileIcon.setImageResource(image)

        val nameToString: String = name.text.toString()

        if(userId % 2 == 0) {
            if (!nameToString.contains(".")) {
                firstInitial.text = nameToString.subSequence(0, 1)
                secondInitial.text = nameToString[spaceIndex(nameToString) + 1].toString()
            } else {
                firstInitial.text = nameToString[spaceIndex(nameToString) + 1].toString()
                secondInitial.text = nameToString[secondSpaceIndex(nameToString) + 1].toString()
            }
        }

        backButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this@UserProfileActivity).toBundle())
        }

    }

    fun spaceIndex(name: String): Int{
        var result : Int = 0
        for(index in name.indices){
            if(name[index].isWhitespace()){
                result = index
                break;
            }
        }
        return result
    }

    fun secondSpaceIndex(name:String): Int{
        var result : Int = 0
        var countSpace: Int = 0
        for(index in name.indices){
            if(name[index].isWhitespace()){
                countSpace++
                if(countSpace == 2){
                    result = index
                    break
                }
            }
        }
        return result
    }


}

