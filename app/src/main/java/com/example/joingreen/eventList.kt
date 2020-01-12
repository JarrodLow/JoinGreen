
//package com.example.joingreen
//
//import android.content.Context
//import android.os.Bundle
//import android.os.PersistableBundle
//import android.util.EventLog
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.*
//import androidx.appcompat.app.AppCompatActivity
//import com.google.firebase.database.DatabaseReference
//import com.google.firebase.database.FirebaseDatabase
//
//class eventList : AppCompatActivity() {
//
//
//    lateinit var showEventDB: DatabaseReference
//    lateinit var eventList: MutableList<eventClass>
//
//    lateinit var showAllEventList: ListView
//    lateinit var joinButton: Button
//    lateinit var textViewEventName: TextView
//    lateinit var textViewEventCreator: TextView
//    lateinit var textViewEventDate: TextView
//    lateinit var textViewEventTime: TextView
//    lateinit var textViewEventLocation: TextView
//    lateinit var textViewAttendance: TextView
//    lateinit var imageViewEventPhoto: ImageView
//
//    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
//        super.onCreate(savedInstanceState, persistentState)
//        setContentView(R.layout.event)
//
//        //link database
//        eventList= mutableListOf()
//        showEventDB=FirebaseDatabase.getInstance().getReference("Event")
//
//        //havent set image view
//        textViewEventName=findViewById(R.id.eventName)
//        textViewEventCreator=findViewById(R.id.eventCreator)
//        textViewEventDate=findViewById(R.id.eventDate)
//        textViewEventTime=findViewById(R.id.eventTime)
//        textViewEventLocation=findViewById(R.id.location)
//        textViewAttendance=findViewById(R.id.attdCode)
//        showAllEventList=findViewById(R.id.eventListView)
//
//        val adapter = eventAdapter(this@eventList,R.layout.event_list,eventList)
//        showAllEventList.adapter=adapter
//
//        joinButton.setOnClickListener{
//            joinEvent();
//        }
//
//    }
//
//    private fun joinEvent(){
//        val joinEventName=textViewEventName.text.toString()
//        //how to get user name
//        val username="username"
//
//        val userJoinEventDB=FirebaseDatabase.getInstance().getReference("JoinedUser")
//        val joinedUserId=userJoinEventDB.push().key.toString()
//        val joinedEventUser=joinedUser(joinedUserId,joinEventName,username)
//
//        userJoinEventDB.child(joinedUserId).setValue(joinedEventUser).addOnCompleteListener() {
//            Toast.makeText(applicationContext,"Joined Successfully",Toast.LENGTH_LONG).show()
//        }
//    }
//
//}
//
//
//

package com.example.joingreen

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.util.EventLog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class eventList : AppCompatActivity() {


    lateinit var showEventDB: DatabaseReference
    lateinit var eventList: MutableList<eventClass>

    lateinit var showAllEventList: ListView
    lateinit var joinButton: Button
    lateinit var textViewEventName: TextView
    lateinit var textViewEventCreator: TextView
    lateinit var textViewEventDate: TextView
    lateinit var textViewEventTime: TextView
    lateinit var textViewEventLocation: TextView
    lateinit var textViewAttendance: TextView

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.event)

        //link database
        eventList= mutableListOf()
        showEventDB=FirebaseDatabase.getInstance().getReference("Event")


        textViewEventName=findViewById(R.id.eventName)
        textViewEventCreator=findViewById(R.id.eventCreator)
        textViewEventDate=findViewById(R.id.eventDate)
        textViewEventTime=findViewById(R.id.eventTime)
        textViewEventLocation=findViewById(R.id.location)
        textViewAttendance=findViewById(R.id.attdCode)
        showAllEventList=findViewById(R.id.eventListView)



        showEventDB.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {

                if(p0!!.exists()){

                    for (h in p0.children){
                        val event=h.getValue(eventClass::class.java)
                        eventList.add(event!!)
                    }

                    val adapter = eventAdapter(this@eventList,R.layout.event_list,eventList)
                    showAllEventList.adapter=adapter
                }
            }
        })

        var haha=findViewById<TextView>(R.id.haha)
        haha.text="hahahaha"

        joinButton.setOnClickListener{
            joinEvent()
        }


    }

    private fun joinEvent(){
        val joinEventName=textViewEventName.text.toString()
        //how to get user name
        val username="username"

        val userJoinEventDB=FirebaseDatabase.getInstance().getReference("JoinedUser")
        val joinedUserId=userJoinEventDB.push().key.toString()
        val joinedEventUser=joinedUser(joinedUserId,joinEventName,username)

        userJoinEventDB.child(joinedUserId).setValue(joinedEventUser).addOnCanceledListener {
            Toast.makeText(applicationContext,"Joined Successfully",Toast.LENGTH_LONG).show()
        }
    }

}



