package com.example.joingreen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import org.w3c.dom.Text

class eventAdapter(val mCtx: Context,val layoutResId: Int,val eventList: List<eventClass>)
    : ArrayAdapter<eventClass>(mCtx, layoutResId, eventList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        lateinit var showEventDB: DatabaseReference
        var auth: FirebaseAuth?=null

        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(layoutResId,null)

        val textViewEventName = view.findViewById<TextView>(R.id.eventName)
        val textViewEventCreator = view.findViewById<TextView>(R.id.eventCreator)
        val textViewEventDate = view.findViewById<TextView>(R.id.eventDate)
        val textViewEventTime = view.findViewById<TextView>(R.id.eventTime)
        val textViewEventLocation = view.findViewById<TextView>(R.id.location)
        val textViewAttCode = view.findViewById<TextView>(R.id.attdCode)
        val joinedButton=view.findViewById<Button>(R.id.joinBtn)

        val event = eventList[position]

        textViewEventName.text=event.eventName
        textViewEventCreator.text=event.creator
        textViewEventDate.text=event.eventDate
        textViewEventTime.text=event.eventStartTime
        textViewEventLocation.text=event.eventLocation
        textViewAttCode.text=event.attCode.toString()

        joinedButton.setOnClickListener {


           // var database :DatabaseReference = FirebaseDatabase.getInstance().reference.child("/users")
            auth = FirebaseAuth.getInstance()
            showEventDB=FirebaseDatabase.getInstance().reference.child("/users")
            val User = auth!!.currentUser
            val UserReference = showEventDB!!.child(User!!.uid)
            var username=""

            UserReference.addValueEventListener(object : ValueEventListener{
                override fun onDataChange(p0: DataSnapshot) {

                    val nameHolder = p0.child("userName").value as String
                    username=nameHolder

                }

                override fun onCancelled(p0: DatabaseError) {
                }
            })

            val joinEventName=textViewEventName.text.toString()

            val userJoinEventDB=FirebaseDatabase.getInstance().getReference("JoinedUser")
            val joinedUserId=userJoinEventDB.push().key.toString()
            val joinedEventUser=joinedUser(joinedUserId,joinEventName,username)

            userJoinEventDB.child(joinedUserId).setValue(joinedEventUser).addOnCanceledListener {

            }
        }


        return view


    }



}

