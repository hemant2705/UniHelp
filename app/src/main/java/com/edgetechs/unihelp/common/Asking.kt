package com.edgetechs.unihelp.common

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import com.edgetechs.unihelp.R
import com.edgetechs.unihelp.Student.Student_Activity

class Asking : AppCompatActivity() {

    lateinit var student :AppCompatButton
    lateinit var  administration:AppCompatButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.asking)
        student=findViewById(R.id.btn_student)
        administration=findViewById(R.id.btn_admin)

        student.setOnClickListener{
            val intent= Intent(this,studentLogin::class.java)
            startActivity(intent)
        }
        administration.setOnClickListener{
            val intent= Intent(this,AdminLogin::class.java)
            startActivity(intent)
        }

    }
    override fun onBackPressed() {
        finishAffinity()
    }

}