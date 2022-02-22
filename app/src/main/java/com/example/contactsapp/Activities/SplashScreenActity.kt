package com.example.contactsapp.Activities

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import com.example.contactsapp.MainActivity
import com.example.contactsapp.R

class SplashScreenActity : AppCompatActivity() {

    private lateinit var handler: Handler
    private lateinit var tvAnim: TextView
    private lateinit var animationTV: Animation
    private lateinit var animationPlant: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen_activity)
        tvAnim = findViewById(R.id.tvAnim)
        animationTV= AnimationUtils.loadAnimation(applicationContext,R.anim.textview_animation)
        //tvAnim.startAnimation(animationTV)
        handler = Handler()
        animationPlant = findViewById(R.id.plantAnim)

            animationPlant.animate().scaleX(1.3f).scaleY(1.3f).alpha(1f).setDuration(2000)
                .withEndAction() {
                    animationPlant.animate().scaleX(1f).scaleY(1f)
                }

        tvAnim.animate().scaleX(1.1f).scaleY(1.1f).alpha(1f).setDuration(2000)
            .withEndAction() {
                tvAnim.animate().scaleX(1f).scaleY(1f)
            }



        handler.postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
            finish()
        },4000)

    }
}

