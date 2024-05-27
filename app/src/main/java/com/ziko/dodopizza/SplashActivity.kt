package com.ziko.dodopizza

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val delays = intArrayOf(1900, 2200, 2000, 2500,2900 )

        val randomDelay = delays.random()

        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val isFirstTime = sharedPreferences.getBoolean("IS_FIRST_TIME", true)

        Handler().postDelayed({


            if (isFirstTime) {
                // Перенаправление на LoginActivity, если это первый запуск
                val intent = Intent(this, LoginUserActivity::class.java)
                startActivity(intent)
            } else {
                // Перенаправление на MainActivity, если это не первый запуск
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            finish()
        }, randomDelay.toLong())



    }
}