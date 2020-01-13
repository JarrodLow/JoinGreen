package com.example.joingreen.ui.event


import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.joingreen.R
import com.example.joingreen.eventClass
import com.example.joingreen.eventList
import com.example.joingreen.rewardList
import com.example.joingreen.ui.home.HomeFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.create_event.*
import kotlinx.android.synthetic.main.event.*
import kotlinx.android.synthetic.main.event.eventDate
import kotlinx.android.synthetic.main.event.eventName
import kotlinx.android.synthetic.main.event.location
import kotlinx.android.synthetic.main.event_list.*
import java.util.*


class EventFragment : Fragment() {
    //firebase
    private var auth: FirebaseAuth? = null

    companion object {
        fun newInstance() = EventFragment()
    }

    private lateinit var eventviewModel: EventViewModel


    var username = ""   //Need to get username with session?

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        val root = inflater.inflate(R.layout.event, container, false)


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        eventDate.setOnClickListener {

            val cal = Calendar.getInstance()

            //get current
            val clYear = cal.get(Calendar.YEAR)
            val clMonth = cal.get(Calendar.MONTH)
            val clDay = cal.get(Calendar.DAY_OF_MONTH)

            val dpd =
                this.activity?.let { it1 ->
                    DatePickerDialog(
                        it1,
                        DatePickerDialog.OnDateSetListener { view, year, month, day ->
                            eventDate.setText(day.toString() + "-" + (month + 1).toString() + "-" + year.toString())

                        },
                        clYear,
                        clMonth,
                        clDay
                    )
                }
            dpd?.show()
            //link firebase

            var database: DatabaseReference =
                FirebaseDatabase.getInstance().reference.child("/users")
            auth = FirebaseAuth.getInstance()
            val User = auth!!.currentUser
            val UserReference = database!!.child(User!!.uid)

            UserReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val holder = snapshot.child("userName").value as String
                    username = holder

                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })
        }

        createEvent.setOnClickListener {
            val eventName = eventName.text.toString()
            val eventDate = eventDate.text.toString()
            val eventStartTime = startTime.text.toString()
            val eventEndTime = endTime.text.toString()
            val eventLocation = location.text.toString()
            val attendance = (100000..999999).random()


            val createEventDB: DatabaseReference =
                FirebaseDatabase.getInstance().getReference("Event")
            val eventId = createEventDB.push().key.toString()
            if (username == "" || eventName == "" || eventDate == "" || eventStartTime == "" || eventEndTime == "" || eventLocation == "") {
                Toast.makeText(
                    this.activity,
                    "Please fill in all required fields",
                    Toast.LENGTH_LONG
                ).show()

            }


            else {
                //save all data into event object
                val newEvent = eventClass(

                    attendance,
                    username,
                    eventDate,
                    eventEndTime,
                    eventId,
                    eventName,
                    eventLocation,
                    eventStartTime


                )

                //add data into database
                createEventDB.child(eventId).setValue(newEvent).addOnCompleteListener {
                    Toast.makeText(
                        this.activity,
                        "Event has been created successfully",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            //}


            val viewEventButton: Button = view.findViewById(R.id.viewEventListButton)
            viewEventButton.setOnClickListener {
                val intent = Intent(context, eventList::class.java)

                startActivity(intent)
            }


        }
    }
}

/*
    private fun eventDate(view: View) {
        //get Input calendar
        val evDate = Calendar.getInstance()

        //get current
        val evYear = evDate.get(Calendar.YEAR)
        val evMonth = evDate.get(Calendar.MONTH)
        val evDay = evDate.get(Calendar.DAY_OF_MONTH)

        val dpd =
            DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, day ->
                eventDate.setText(day.toString() + "-" + month.toString() + "-" + year.toString())
            }, evYear, evMonth, evDay)
        dpd.show()
    }*/


