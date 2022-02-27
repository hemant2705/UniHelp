package com.edgetechs.unihelp.common

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.edgetechs.unihelp.Admin.AdminActivity
import com.edgetechs.unihelp.R
import com.edgetechs.unihelp.Student.Student_Activity
import com.google.android.material.textfield.TextInputLayout

class AdminLogin : AppCompatActivity() {


    lateinit var loginBtn : Button
    lateinit var finalphone :String
    lateinit var finalpass:String
    lateinit var phone : TextInputLayout
    lateinit var pass : TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)


        loginBtn=findViewById(R.id.btnLogin)

        phone = findViewById(R.id.phones)
        pass = findViewById(R.id.pass)

        val ntent= Intent(this, AdminActivity::class.java)

        loginBtn.setOnClickListener {
            finalphone = phone.editText!!.text.toString()
            finalpass = pass.editText!!.text.toString()

            if (finalphone == "1000" && finalpass == "123") {
                startActivity(ntent)
            } else {
                Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show()
            }
        }
    }

}