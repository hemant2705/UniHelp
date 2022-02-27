package com.edgetechs.unihelp.Admin.frags

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.edgetechs.unihelp.Admin.Adapter.personAdapter
import com.edgetechs.unihelp.R
import com.edgetechs.unihelp.model.person

import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.*


class AdminHome : Fragment() {

    lateinit var recyclerView: RecyclerView
    var adapter : personAdapter? = null
    var mbase : DatabaseReference? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      /*  val view=inflater.inflate(R.layout.fragment_admin_home, container, false)

        mbase = FirebaseDatabase.getInstance().getReference("NAME")
       // recyclerView = view.findViewById(R.id.recycler1)
        recyclerView.layoutManager = LinearLayoutManager(activity)


        val options = FirebaseRecyclerOptions.Builder<person>()
            .setQuery(mbase!!, person::class.java)
            .build()
        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter = personAdapter(options)
        // Connecting Adapter class with the Recycler view
        recyclerView.setAdapter(adapter)*/

        return  view
    }
    override fun onStart() {
        super.onStart()
        adapter!!.startListening()
    }

    // Function to tell the app to stop getting
    // data from database on stopping of the activity
    override fun onStop() {
        super.onStop()
        adapter!!.stopListening()
    }



}