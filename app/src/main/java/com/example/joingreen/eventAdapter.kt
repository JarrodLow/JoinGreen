package com.example.joingreen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter

class eventAdapter(val mCtx: Context,val layoutResId: Int,val eventList: List<eventClass>)
    : ArrayAdapter<eventClass>(mCtx, layoutResId, eventList) {

    val layoutInflater : LayoutInflater = LayoutInflater.from(mCtx)
    val view : View = layoutInflater.inflate(layoutResId,null)


}