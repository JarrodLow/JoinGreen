//package com.example.joingreen
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.view.View
//import android.widget.EditText
//import android.widget.ImageView
//import android.widget.Toast
//import com.bumptech.glide.Glide
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.database.*
//import kotlinx.android.synthetic.main.create_event.*
//import java.util.*
//
//class createEvent : AppCompatActivity() {
//
//    //firebase
//    private var databaseReference: DatabaseReference? = null
//    private var database: FirebaseDatabase? = null
//    private var auth: FirebaseAuth? = null
//
//    var username = ""   //Need to get username with session?
//    lateinit var editTextEventName : EditText
//    lateinit var editTextEventDate : EditText
//    lateinit var editTextStartTime : EditText
//    lateinit var editTextEndTime : EditText
//    lateinit var editTextLocation : EditText
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.create_event)
//
//        editTextEventName= findViewById(R.id.eventName)
//        editTextEventDate=findViewById(R.id.eventDate)
//        editTextStartTime=findViewById(R.id.startTime)
//        editTextEndTime=findViewById(R.id.endTime)
//        editTextLocation=findViewById(R.id.location)
//
//        createEvent.setOnClickListener {
//           addEvent(it)
//            clearForm(it)
//        }
//
//    }
//
//    private fun addEvent(view: View){
//        //save all user entered details
//        val eventName=editTextEventName.text.toString()
//        val eventDate=editTextEventDate.text.toString()
//        val eventStartTime=editTextStartTime.text.toString()
//        val eventEndTime=editTextEndTime.text.toString()
//        val eventLocation=editTextLocation.text.toString()
//        val attendance=(100000..999999).random()
//
//        //link firebase
//        val createEventDB=FirebaseDatabase.getInstance().getReference("Event")
//        val eventId=createEventDB.push().key.toString()
//        database = FirebaseDatabase.getInstance()
//        databaseReference = database!!.reference!!.child("/users")
//        auth = FirebaseAuth.getInstance()
//        val User = auth!!.currentUser
//        val UserReference = databaseReference!!.child(User!!.uid)
//        UserReference.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                username = snapshot.child("userName").value as String
//
//            }
//            override fun onCancelled(databaseError: DatabaseError) {}
//        })
//
//
//        if(username=="" || eventName=="" || eventDate=="" || eventStartTime=="" || eventEndTime=="" || eventLocation==""){
//            Toast.makeText(applicationContext,"Please fill in all required fields", Toast.LENGTH_LONG).show()
//            return
//        }
//
//        //save all data into event object
//        val newEvent=eventClass(eventId,username,eventName,eventDate,eventStartTime,eventEndTime,eventLocation,attendance)
//
//        //add data into database
//        createEventDB.child(eventId).setValue(newEvent).addOnCompleteListener{
//            Toast.makeText(applicationContext,"Event has been created successfully", Toast.LENGTH_LONG).show()
//        }
//
//    }
//
//    private fun clearForm(view: View){
//        eventName.text.clear()
//        eventDate.text.clear()
//        startTime.text.clear()
//        endTime.text.clear()
//        location.text.clear()
//    }
//}