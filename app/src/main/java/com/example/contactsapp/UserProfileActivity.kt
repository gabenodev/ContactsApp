package com.example.contactsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView

class UserProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        val name: TextView = findViewById(R.id.userName)
        val email: TextView = findViewById(R.id.userEmail)
        val profileIcon: ImageView = findViewById(R.id.profileAvatar)
        val firstInitial: TextView = findViewById(R.id.fInitial)
        val secondInitial: TextView = findViewById(R.id.sInitial)
        val id: Int = intent.getIntExtra("id",1)
        Log.e("id", "The id of the user given to me is " + id.toString())

        val image: Int = intent.getIntExtra("image",R.drawable.initials_bg)

        name.text = intent.getStringExtra("name")
        email.text = intent.getStringExtra("email")
        profileIcon.setImageResource(image)

        val nameToString: String = name.text.toString()

        if(id % 2 == 0) {
            if (!nameToString.contains(".")) {
                firstInitial.text = nameToString.subSequence(0, 1)
                secondInitial.text = nameToString[spaceIndex(nameToString) + 1].toString()
            } else {
                firstInitial.text = nameToString[spaceIndex(nameToString) + 1].toString()
                secondInitial.text = nameToString[secondSpaceIndex(nameToString) + 1].toString()
            }
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

