package com.example.joingreen

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.profile.*
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS

class birthDateC : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)

        birthDate.setOnClickListener {
            birthDate1(it)
        }
    }

    private fun birthDate1(view: View) {
        //get Input calendar
        val birthDate1 = Calendar.getInstance()

        //get current
        val birthYear = birthDate1.get(Calendar.YEAR)
        val birthMonth = birthDate1.get(Calendar.MONTH)
        val birthDay = birthDate1.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, day ->
                birthDate.setText(day.toString() + "-" + month.toString() + "-" + year.toString())
            }, birthYear, birthMonth, birthDay)
        dpd.show()
    }
}

