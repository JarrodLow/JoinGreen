package com.example.joingreen

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.joingreen.ui.home.HomeViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class eventList : AppCompatActivity() {

    //Firebase authentication

    private var auth: FirebaseAuth?=null

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



    lateinit var rewardViewModel: RewardViewModel
    lateinit var sharedPreferences : SharedPreferences

    var array= arrayOfNulls<String>(10)

    var mPoints = arrayOf("1000", "1500", "1500", "1500", "2000")
    var images = intArrayOf(
        R.drawable.reward1,
        R.drawable.reward2,
        R.drawable.reward3,
        R.drawable.reward4,
        R.drawable.reward5

    )


    private var databaseReference: DatabaseReference? = null
    private var database: FirebaseDatabase? = null


    private lateinit var homeViewModel: HomeViewModel

    private val currentUser = FirebaseAuth.getInstance().currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_event)
        val listView:ListView = findViewById(R.id.eventListView)
        // now create an adapter class


        //retrieve current user data
        eventList= mutableListOf()
        showEventDB=FirebaseDatabase.getInstance().getReference("Event")
        //retrieve current user data




        showEventDB.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {


                        eventList.clear()
                    for (h in p0.children){
                        val event=h.getValue(eventClass::class.java)
                        eventList.add(event!!)

                }
                val adapter = eventAdapter(applicationContext,R.layout.event_list,eventList)
                listView.adapter=adapter
            }
        })







        // so item click is done now check list view
    }


    internal inner class MyAdapter(
        context: Context,
        var rTitle: Array<String>


    ) : ArrayAdapter<String?>(context, R.layout.event_list,rTitle) {
        override fun getView(
            position: Int,
            convertView: View?,
            parent: ViewGroup
        ): View {

            val layoutInflater =
                applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val row = layoutInflater.inflate(R.layout.event_list, parent, false)
            val textViewEventName = row.findViewById<TextView>(R.id.eventName)
            val textViewEventCreator = row.findViewById<TextView>(R.id.eventCreator)
            val textViewEventDate = row.findViewById<TextView>(R.id.eventDate)
            val textViewEventTime = row.findViewById<TextView>(R.id.eventTime)
            val textViewEventLocation = row.findViewById<TextView>(R.id.location)
            val textViewAttCode = row.findViewById<TextView>(R.id.attdCode)
            // now set our resources on views





            return row
        }

    }



    private fun joinEvent(){

        var database :DatabaseReference = FirebaseDatabase.getInstance().reference.child("/users")
        auth = FirebaseAuth.getInstance()
        val User = auth!!.currentUser
        val UserReference = database!!.child(User!!.uid)
        var userName=""

        UserReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                val nameHolder = p0.child("userName").value as String
                userName = nameHolder
            }

            override fun onCancelled(p0: DatabaseError) {
            }
        })

        val joinEventName=textViewEventName.text.toString()

        val userJoinEventDB=FirebaseDatabase.getInstance().getReference("JoinedUser")
        val joinedUserId=userJoinEventDB.push().key.toString()
        val joinedEventUser=joinedUser(joinedUserId,joinEventName,userName)

        userJoinEventDB.child(joinedUserId).setValue(joinedEventUser).addOnCanceledListener {
            Toast.makeText(applicationContext,"Joined Successfully",Toast.LENGTH_LONG).show()
        }
    }

}



