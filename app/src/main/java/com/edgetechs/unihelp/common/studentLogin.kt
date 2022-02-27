package com.edgetechs.unihelp.common

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.agrawalsuneet.dotsloader.loaders.LazyLoader
import com.edgetechs.unihelp.R
import com.edgetechs.unihelp.Student.Student_Activity
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class studentLogin : AppCompatActivity() {
    lateinit var signup:Button
    lateinit var loginBtn :Button

    lateinit var phone : TextInputLayout
    lateinit var pass : TextInputLayout
    lateinit var finalphone :String
    lateinit var finalpass:String
    lateinit var loader: LazyLoader


    lateinit var str1: String
    lateinit var str2: String
    lateinit var str3: String
    lateinit var str4:String
    lateinit var str5:String
    lateinit var str6:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_login)

        signup = findViewById(R.id.btnsgn)

        loginBtn=findViewById(R.id.btnLogin)

        phone = findViewById(R.id.phones)
        pass = findViewById(R.id.pass)
        loader=findViewById(R.id.loader)


        val ntent=Intent(this, Student_Activity::class.java)

        loginBtn.setOnClickListener {
            finalphone = phone.editText!!.text.toString()
            finalpass = pass.editText!!.text.toString()
            loader.visibility = View.VISIBLE
            val checkuser =
                FirebaseDatabase.getInstance().getReference("STUDENTS").orderByChild("mobile")
                    .equalTo(finalphone)
            checkuser.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@studentLogin,"$error", Toast.LENGTH_SHORT).show()
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists())
                    {
                        phone.error=null
                        phone.isErrorEnabled=false

                        val pa = snapshot.child(finalphone).child("password").getValue().toString()
                        if (pa.equals(finalpass)){
                            pass.error=null
                            pass.isErrorEnabled=false


                            str1=snapshot.child(finalphone).child("fullname").getValue().toString()
                            str2=snapshot.child(finalphone).child("institutename").getValue().toString()
                            str3=snapshot.child(finalphone).child("enrolment").getValue().toString()
                            str4=snapshot.child(finalphone).child("emailAddress").getValue().toString()
                            str5=snapshot.child(finalphone).child("mobile").getValue().toString()


                            val sharedPref = getSharedPreferences("theKey", Context.MODE_PRIVATE)
                            val editor = sharedPref.edit()
                            editor.putString("fullname", str1)
                            editor.putString("institutename", str2)
                            editor.putString("enrolment", str3)
                            editor.putString("emailAddress", str4)
                            editor.putString("mobile",str5)
                            editor.putBoolean("status",true)
                            editor.apply()


                            startActivity(ntent)
                        }
                        else{
                            loader.visibility = View.INVISIBLE
                            Toast.makeText(this@studentLogin,"Password Invalid", Toast.LENGTH_SHORT).show()

                        }
                    }

                    else
                    {
                        loader.visibility = View.INVISIBLE
                        Toast.makeText(this@studentLogin,"No such user found", Toast.LENGTH_SHORT).show()
                    }
                }


            })}



        signup.setOnClickListener {
            val intent = Intent(this, StudentSign::class.java)
            startActivity(intent)
        }
    }
}