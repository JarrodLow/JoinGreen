package com.example.joingreen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class attAdapter(val mCtx: Context,val layoutResId: Int,val eventList: List<eventClass>)
    : ArrayAdapter<eventClass>(mCtx, layoutResId, eventList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {


        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(layoutResId, null)



        val editTextAttn = view.findViewById<EditText>(R.id.attdText)

        val getEvent = eventList[position]

        val eventAttendance=getEvent.attCode.toString()

        if(editTextAttn.text.equals(eventAttendance)){
            Toast.makeText(mCtx,"Signed Successfully", Toast.LENGTH_LONG).show()
            return view
        }

        Toast.makeText(mCtx,"Failed to Sign Attendance", Toast.LENGTH_LONG).show()


        return view
    }
}