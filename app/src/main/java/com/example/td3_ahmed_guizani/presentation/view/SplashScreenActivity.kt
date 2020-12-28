package com.example.td3_ahmed_guizani.presentation.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.td3_ahmed_guizani.R

class SplashScreenActivity : AppCompatActivity() {
    private val SPLASH8SCREEN8TIMEOUT = 5000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        //rediriger vers la liste des pokemon apres 5 secondes
        Handler().postDelayed({ // passer a la fragment de list des pokemon
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH8SCREEN8TIMEOUT.toLong())
    }
}