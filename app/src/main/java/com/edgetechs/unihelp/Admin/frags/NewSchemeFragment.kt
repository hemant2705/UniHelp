package com.edgetechs.unihelp.Admin.frags

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.edgetechs.unihelp.Admin.AdminActivity
import com.edgetechs.unihelp.R
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.FirebaseDatabase


//
class NewSchemeFragment : Fragment() {

lateinit var type: RadioGroup
lateinit var btn1:RadioButton
    lateinit var description:TextInputLayout
    lateinit var code:TextInputLayout
    lateinit var btn:Button

    lateinit var str1:String
    lateinit var str2:String
    lateinit var str3:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_new_scheme, container, false)

        type=view.findViewById(R.id.type)
        description=view.findViewById(R.id.desc)
        code=view.findViewById(R.id.code)
        btn=view.findViewById(R.id.button)



        btn.setOnClickListener {

            btn1 = view.findViewById(type.checkedRadioButtonId)
            str1 = btn1.text.toString()
            str2 = description.editText!!.text.toString()
            str3 = code.editText!!.text.toString()

           new()

            val intent = Intent(activity, AdminActivity::class.java)
            startActivity(intent)


        }

        return view
    }

    fun new(){
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Schemes")
        val addnew: DataHelper1 =
            DataHelper1(
                str1,
                str2,
                str3
            )
        myRef.child(str3).setValue(addnew)}

    }