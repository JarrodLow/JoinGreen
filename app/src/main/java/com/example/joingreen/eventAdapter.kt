package com.example.joingreen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text

class eventAdapter(val mCtx: Context,val layoutResId: Int,val eventList: List<eventClass>)
    : ArrayAdapter<eventClass>(mCtx, layoutResId, eventList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {


        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(layoutResId, null)

        //image havent
        //val imageViewEventPhoto=view.findViewById<ImageView>(R.id.eventImageView)
        val textViewEventName = view.findViewById<TextView>(R.id.eventName)
        val textViewEventCreator = view.findViewById<TextView>(R.id.eventCreator)
        val textViewEventDate = view.findViewById<TextView>(R.id.eventDate)
        val textViewEventTime = view.findViewById<TextView>(R.id.eventTime)
        val textViewEventLocation = view.findViewById<TextView>(R.id.location)
        val textViewAttCode = view.findViewById<TextView>(R.id.attdCode)

        val event = eventList[position]

        //imageViewEventPhoto=
        textViewEventName.text=event.eventName
        textViewEventCreator.text=event.creator
        textViewEventDate.text=event.eventDate
        textViewEventTime.text=event.eventStartTime
        textViewEventLocation.text=event.eventLocation
        textViewAttCode.text=event.attCode.toString()

        return view
    }
}