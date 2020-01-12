package com.example.joingreen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.joingreen.ui.home.HomeViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_home.*

class testing(){

    private var databaseReference: DatabaseReference? = null
    private var database: FirebaseDatabase? = null
    private var auth: FirebaseAuth? = null

    private lateinit var homeViewModel: HomeViewModel

    private val currentUser = FirebaseAuth.getInstance().currentUser
    private fun retrieveUserProfile()
    {
        database = FirebaseDatabase.getInstance()
        databaseReference = database!!.reference!!.child(("/Event"))

        //retrieve current user data
        auth = FirebaseAuth.getInstance()

        val User = auth!!.currentUser
        val UserReferences = databaseReference!!.child((User!!.uid))

        UserReferences.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val EventName = snapshot.child("eventName").value as String

                val EventCreator = snapshot.child("creator").value as String

                val EventDate = snapshot.child("eventDate").value as String

                val EventTime = snapshot.child("eventStartTime").value as String

                val EventLocation = snapshot.child("eventLocation").value as String

                val Attendance = snapshot.child("attCode").value as Int

            }
            override fun onCancelled(databaseError: DatabaseError) {}
        })




    }

}