package com.edgetechs.unihelp.common

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.chaos.view.PinView
import com.edgetechs.unihelp.R
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class Otp : AppCompatActivity() {

    lateinit var pinFromUser: PinView
    lateinit var Login: Button
    lateinit var phone: String
    lateinit var codeBySystem:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)
        pinFromUser = findViewById(R.id.pinView)
        Login = findViewById(R.id.submit)

        val sharedPreferences = getSharedPreferences("myKey", Context.MODE_PRIVATE)
        phone = "+91" + sharedPreferences.getString("mobile", "0").toString()
        sendVerificationCodeToUser(phone)
        Login.setOnClickListener {
            val io = pinFromUser.text.toString()

            verifyCode(io)

        }
    }

    private fun sendVerificationCodeToUser(phoneNo: String) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            phoneNo,  // Phone number to verify
            60,  // Timeout duration
            TimeUnit.SECONDS,  // Unit of timeout
            this,  // Activity (for callback binding)
            mCallbacks
        ) // OnVerificationStateChangedCallbacks
    }
    private val mCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks =
        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onCodeSent(
                s: String,
                forceResendingToken: PhoneAuthProvider.ForceResendingToken
            ) {
                super.onCodeSent(s, forceResendingToken)
                codeBySystem = s
            }

            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                val code = phoneAuthCredential.smsCode
                Toast.makeText(this@Otp, "Otp sent succesfully", Toast.LENGTH_SHORT).show()
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Toast.makeText(this@Otp, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    private fun verifyCode(code: String) {
        val credential = PhoneAuthProvider.getCredential(codeBySystem, code)
        signInWithPhoneAuthCredential(credential)
    }
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        val firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) {
                    //Verification completed successfully here Either
                    // store the data or do whatever desire

                    val intent = Intent(this, confirmation2::class.java)
                    startActivity(intent)
                } else {
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {

                        Toast.makeText(
                            this@Otp,
                            "Verification Not Completed! Try again.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
    }
    }