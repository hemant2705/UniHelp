package com.edgetechs.unihelp.common

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import androidx.core.content.ContextCompat
import com.agrawalsuneet.dotsloader.loaders.LazyLoader
import com.edgetechs.unihelp.R
import com.google.firebase.database.FirebaseDatabase

class confirmation2 : AppCompatActivity() {

    lateinit var value1: String
    lateinit var value2: String
    lateinit var value3: String
    lateinit var value4: String
    lateinit var value5: String
    lateinit var value6: String

    lateinit var submit: Button
    lateinit var prog : LazyLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation2)


        submit=findViewById(R.id.sub)
        prog=findViewById(R.id.loader1)


        val sharedPref = getSharedPreferences("myKey", Context.MODE_PRIVATE)
        value1 = sharedPref.getString("fullname", "0").toString();
        value2 = sharedPref.getString("institutename", "0").toString()
        value3 = sharedPref.getString("enrolment", "0").toString()
        value4 = sharedPref.getString("emailAddress", "0").toString()
        value5 = sharedPref.getString("mobile", "0").toString()
        value6 = sharedPref.getString("password", "0").toString()

        var lazyLoader = LazyLoader(this, 15, 5,
            ContextCompat.getColor(this,
                R.color.loader_selected
            ),
            ContextCompat.getColor(this,
                R.color.loader_selected
            ),
            ContextCompat.getColor(this,
                R.color.loader_selected
            ))
            .apply {
                animDuration = 500
                firstDelayDuration = 100
                secondDelayDuration = 200
                interpolator = DecelerateInterpolator()
            }
        prog.addView(lazyLoader)

        submit.setOnClickListener {

            new()
            prog.visibility= View.VISIBLE
            val intent = Intent(this,
                studentLogin::class.java)
            startActivity(intent)
        }
    }
    fun new(){
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("STUDENTS")
        val addnew: DataHelper =
            DataHelper(
                value1,
                value2,
                value3,
                value4,
                value5,
                value6
            )
        myRef.child(value5).setValue(addnew)
    }

}