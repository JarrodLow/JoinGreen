package com.example.joingreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.create_event.*
import java.util.*

class createEvent : AppCompatActivity() {

    var username = "username"   //Need to get username with session?
    lateinit var editTextEventName : EditText
    lateinit var editTextEventDate : EditText
    lateinit var editTextStartTime : EditText
    lateinit var editTextEndTime : EditText
    lateinit var editTextLocation : EditText
    lateinit var imageViewEventPhoto: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_event)

        //save photo variables
        imageViewEventPhoto=findViewById(R.id.addPic)
        editTextEventName= findViewById(R.id.eventName)
        editTextEventDate=findViewById(R.id.eventDate)
        editTextStartTime=findViewById(R.id.startTime)
        editTextEndTime=findViewById(R.id.endTime)
        editTextLocation=findViewById(R.id.location)

        createEvent.setOnClickListener {
           // addEvent(it)
            clearForm(it)
        }

    }

   /* private fun addEvent(view: View){
        //save all user entered details
        //val eventPhoto=imageViewEventPhoto.
        val eventCreator=username;
        val eventName=editTextEventName.text.toString()
        val eventDate=editTextEventDate.text.toString()
        val eventStartTime=editTextStartTime.text.toString()
        val eventEndTime=editTextEndTime.text.toString()
        val eventLocation=editTextLocation.text.toString()
        val attendance=(100000..999999).random()

        //link firebase
        val createEventDB=FirebaseDatabase.getInstance().getReference("Event")
        val eventId=createEventDB.push().key.toString()

        if(eventCreator==null && eventName==null && eventDate==null && eventStartTime==null && eventEndTime==null && eventLocation==null){
            Toast.makeText(applicationContext,"Please fill in all required fields", Toast.LENGTH_LONG).show()
            return
        }

        //save all data into event object
        val newEvent=eventClass(eventId,eventCreator,eventName,eventDate,eventStartTime,eventEndTime,eventLocation,attendance)

        //add data into database
        createEventDB.child(eventId).setValue(newEvent).addOnCompleteListener{
            Toast.makeText(applicationContext,"Event has been created successfully", Toast.LENGTH_LONG).show()
        }

    }*/

    private fun clearForm(view: View){
        addPic.setImageBitmap(null)
        eventName.text.clear()
        eventDate.text.clear()
        startTime.text.clear()
        endTime.text.clear()
        location.text.clear()
    }
}