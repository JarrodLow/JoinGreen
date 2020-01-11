package com.example.joingreen

import android.app.Application
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

        val textViewAttendance = view.findViewById<TextView>(R.id.textViewAttendance)

        val editTextAttn = view.findViewById<EditText>(R.id.attdText)

        val getEvent = eventList[position]
        val attCodeInDB= getEvent.attCode.toString()
        //textViewAttendance.text=getEvent.attCode.toString()

        if(editTextAttn.text.equals(attCodeInDB)){
            textViewAttendance.text="yes"
        }

        return view
    }
}