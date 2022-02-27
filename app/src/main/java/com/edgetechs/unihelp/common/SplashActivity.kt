package com.edgetechs.unihelp.common

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.edgetechs.unihelp.Admin.AdminActivity
import com.edgetechs.unihelp.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed({
            val intent =
                Intent(this@SplashActivity, Asking::class.java)
            startActivity(intent)
        }, 4000)
    }
}