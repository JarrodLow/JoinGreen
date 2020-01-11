package com.example.joingreen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class eventList : AppCompatActivity() {
    var mTitle =
        arrayOf("Facebook", "Whatsapp", "Twitter", "Instagram", "Youtube")
    var mDescription = arrayOf("Facebook Description", "Whatsapp Description", "Twitter Description", "Instagram Description", "Youtube Description")
    var images = intArrayOf(
        R.drawable.facebook,
        R.drawable.whatsapp,
        R.drawable.twitter,
        R.drawable.instagram,
        R.drawable.youtube
    )
    var mButton: Button? = null
    // so our images and other things are set in array
// now paste some images in drawable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.event)
        val listView:ListView = findViewById(R.id.eventListView)
        // now create an adapter class
        mButton = findViewById(R.id.joinBtn)
        val adapter = MyAdapter(this, mTitle, mDescription, images, mButton)
        listView.setAdapter(adapter)

        // so item click is done now check list view
    }


    internal inner class MyAdapter(
        context: Context,
        var rTitle: Array<String>,
        var rDescription: Array<String>,
        var rImgs: IntArray,
        var rButton: Button?
    ) : ArrayAdapter<String?>(context, R.layout.event_list, R.id.eventName, rTitle) {
        override fun getView(
            position: Int,
            convertView: View?,
            parent: ViewGroup
        ): View {
            val layoutInflater =
                applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val row = layoutInflater.inflate(R.layout.event_list, parent, false)
            val images =
                row.findViewById<ImageView>(R.id.eventImageView)
            val myTitle = row.findViewById<TextView>(R.id.eventName)
            val myDescription = row.findViewById<TextView>(R.id.eventDescription)
            val myButton =
                row.findViewById<Button>(R.id.joinBtn)
            // now set our resources on views
            images.setImageResource(rImgs[position])
            myTitle.text = rTitle[position]
            myDescription.text = rDescription[position]
            myButton.setOnClickListener {
                Toast.makeText(this@eventList, "Youtube Description", Toast.LENGTH_SHORT)
                    .show()
            }
            return row
        }
    }
}
