package com.example.joingreen

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.create_event.*
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS

class date : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_event)

        eventDate.setOnClickListener {
            eventDate(it)
        }
    }

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
    }
}