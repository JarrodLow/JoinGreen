package com.example.joingreen

import android.app.usage.UsageEvents
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import kotlinx.android.synthetic.main.attendance.*
import kotlinx.android.synthetic.main.create_event.*
import java.util.*
import com.google.firebase.database.*
import kotlin.math.sign

class signAttendance : AppCompatActivity() {

    lateinit var code : EditText
    lateinit var createEventDB : DatabaseReference
    lateinit var eventClassList: MutableList<eventClass>
    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.attendance)

        //Link database to retrieve
        eventClassList= mutableListOf()
        createEventDB=FirebaseDatabase.getInstance().getReference("Event")

        listView=findViewById(R.id.)


        attdBtn.setOnClickListener {
            sign(it)
        }

        createEventDB.addValueEventListener(object: ValueEventListener {

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            //Start from here u implement the adapter
            override fun onDataChange(p0: DataSnapshot) {

                if (p0!!.exists()) {
                    eventClassList.clear()
                    //Loop all data from database into the list
                    for (h in p0.children) {
                        val event = h.getValue(eventClass::class.java)
                        if (code.text.toString() == event)//not done

                            eventClassList.add(event!!)
                    }
                }
            }
        })
    }
    private fun sign(view: View){
        code = findViewById(R.id.attdText)
        val compare=code.text.toString()


    }

}