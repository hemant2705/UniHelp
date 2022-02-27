package com.edgetechs.unihelp.Admin.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.edgetechs.unihelp.R
import com.edgetechs.unihelp.model.person
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import java.util.*


class personAdapter(
    @NonNull options: FirebaseRecyclerOptions<person>?
) :
    FirebaseRecyclerAdapter<person, personAdapter.personsViewholder>(options!!) {

    protected override fun onBindViewHolder(
        @NonNull holder: personsViewholder,
        position: Int, @NonNull model: person
    ) {
        holder.firstname.setText(model.firstname)


        holder.lastname.setText(model.lastname)

        holder.age.setText(model.age)
    }

    // Function to tell the class about the Card view (here
    // "person.xml")in
    // which the data will be shown
    @NonNull
    override fun onCreateViewHolder(
        @NonNull parent: ViewGroup,
        viewType: Int
    ): personsViewholder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item,parent, false)
        return personsViewholder(view)
    }

    inner class personsViewholder(@NonNull itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var firstname: TextView
        var lastname: TextView
        var age: TextView

        init {
            firstname = itemView.findViewById(R.id.firstname)
            lastname = itemView.findViewById(R.id.lastname)
            age = itemView.findViewById(R.id.age)
        }
    }
}