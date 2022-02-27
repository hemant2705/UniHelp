package com.edgetechs.unihelp.common

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.edgetechs.unihelp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    override fun onBackPressed() {
        finishAffinity()
    }

}