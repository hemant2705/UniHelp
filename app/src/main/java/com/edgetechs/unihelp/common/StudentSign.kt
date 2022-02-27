package com.edgetechs.unihelp.common

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.edgetechs.unihelp.R
import com.google.android.material.textfield.TextInputLayout

class StudentSign : AppCompatActivity() {

    lateinit var   nextButton: Button
    lateinit var back: ImageView
    lateinit var fullname: TextInputLayout
    lateinit var institutename: TextInputLayout
    lateinit var enrolment: TextInputLayout
    lateinit var emailAddress: TextInputLayout
    lateinit var mobile: TextInputLayout
    lateinit var password: TextInputLayout

    lateinit var str1: String
    lateinit var str2: String
    lateinit var str3: String
    lateinit var str4: String
    lateinit var str5: String
    lateinit var str6: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_sign)

        nextButton = findViewById(R.id.nexti)
        back = findViewById(R.id.iconRet)
        fullname = findViewById(R.id.fullname)
        mobile = findViewById(R.id.mobile)
        enrolment = findViewById(R.id.rollNumber)
        institutename = findViewById(R.id.instituteName)
        emailAddress = findViewById(R.id.email)
        password = findViewById(R.id.password)
        back.setOnClickListener {
            onBackPressed()
        }
        nextButton.setOnClickListener {
            str1 = fullname.editText!!.text.toString()
            str2 = institutename.editText!!.text.toString()
            str3 = enrolment.editText!!.text.toString()
            str4 = emailAddress.editText!!.text.toString()
            str5 = mobile.editText!!.text.toString()
            str6 = password.editText!!.text.toString()

            if (!validateFullname() || !validateEmailname() || !validatePassword() || !validatephone()) {
                return@setOnClickListener
            } else {
                val sharedPref = getSharedPreferences("myKey", Context.MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putString("fullname", str1)
                editor.putString("institutename", str2)
                editor.putString("enrolment", str3)
                editor.putString("emailAddress", str4)
                editor.putString("mobile",str5)
                editor.putString("password", str6)
                editor.apply()
                // val intent = Intent(this, merchantSignUp2::class.java)
                //startActivity(intent)

                val intent = Intent(this, Otp::class.java)
                startActivity(intent)
            }
        }
    }
        fun validateFullname(): Boolean {
            val test = fullname.editText!!.text.toString().trim()
            if (test.isEmpty()) {
                fullname.error = "Field cannot be empty"
                return false
            }

            else {
                fullname.error = null
                fullname.isErrorEnabled = false
                return true
            }
        }


        fun validateEmailname(): Boolean {
            val test: String = emailAddress.editText!!.text.toString().trim()
            val checkEmail:Regex = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+".toRegex()

            if (test.isEmpty()) {
                emailAddress.error = "Field cannot be empty"
                return false
            }
            else if (!test.contains(checkEmail)) {
                emailAddress.error = "Invalid Email"
                return false
            } else {
                emailAddress.error = null
                emailAddress.isErrorEnabled = false
                return true
            }
        }

        fun validatePassword(): Boolean {
            val test: String = password.editText!!.text.toString().trim()

            if (test.isEmpty()) {
                password.error = "Field cannot be empty"
                return false
            }
            else /*if (!test.contains(checkPassword)) {
            password.error = "Atleast 4 characters"
            return false
        } else*/ {
                password.error = null
                password.isErrorEnabled = false
                return true
            }
        }

        fun validatephone(): Boolean {
            val test = mobile.editText!!.text.toString().trim()
            if (test.isEmpty()) {
                mobile.error = "Field cant be empty"
                return false
            } else if (test.length !== 10) {
                mobile.error = "Enter a valid Number"
                return false
            } else {
                mobile.error = null
                mobile.isErrorEnabled = false
                return true
            }


        }
}