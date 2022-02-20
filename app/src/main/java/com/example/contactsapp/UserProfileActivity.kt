package com.example.contactsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class UserProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        val name: TextView = findViewById(R.id.userName)
        val email: TextView = findViewById(R.id.userEmail)
        val profileIcon: ImageView = findViewById(R.id.profileAvatar)

        val image: Int = intent.getIntExtra("image",R.drawable.profile_icon3)

        name.text = intent.getStringExtra("name")
        email.text = intent.getStringExtra("email")
        profileIcon.setImageResource(image)

    }
}

