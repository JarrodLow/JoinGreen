package com.example.mohsin.customlistview

import android.R
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var listView: ListView? = null
    var mTitle =
        arrayOf("Facebook", "Whatsapp", "Twitter", "Instagram", "Youtube")
    var mDescription = arrayOf(
        "Facebook Description",
        "Whatsapp Description",
        "Twitter Description",
        "Instagram Description",
        "Youtube Description"
    )
    var images = intArrayOf(
        R.drawable.facebook,
        R.drawable.whatsapp,
        R.drawable.,
        R.drawable.instagram,
        R.drawable.youtube
    )

    // so our images and other things are set in array
// now paste some images in drawable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listView = findViewById(R.id.listView)
        // now create an adapter class
        val adapter =
            MyAdapter(
                this,
                mTitle,
                mDescription,
                images
            )
        listView.setAdapter(adapter)
        // there is my mistake...
// now again check this..
// now set item click on list view
        listView.setOnItemClickListener(OnItemClickListener { parent, view, position, id ->
            if (position == 0) {
                Toast.makeText(this@MainActivity, "Facebook Description", Toast.LENGTH_SHORT)
                    .show()
            }
            if (position == 0) {
                Toast.makeText(this@MainActivity, "Whatsapp Description", Toast.LENGTH_SHORT)
                    .show()
            }
            if (position == 0) {
                Toast.makeText(this@MainActivity, "Twitter Description", Toast.LENGTH_SHORT)
                    .show()
            }
            if (position == 0) {
                Toast.makeText(this@MainActivity, "Instagram Description", Toast.LENGTH_SHORT)
                    .show()
            }
            if (position == 0) {
                Toast.makeText(this@MainActivity, "Youtube Description", Toast.LENGTH_SHORT)
                    .show()
            }
        })
        // so item click is done now check list view
    }

    internal inner class MyAdapter(
        var context: Context,
        var rTitle: Array<String>,
        var rDescription: Array<String>,
        var rImgs: IntArray
    ) :
        ArrayAdapter<String?>(context, R.layout.row, R.id.textView1, rTitle) {
        override fun getView(
            position: Int, @Nullable convertView: View?,
            parent: ViewGroup
        ): View {
            val layoutInflater =
                applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val row: View = layoutInflater.inflate(R.layout.row, parent, false)
            val images =
                row.findViewById<ImageView>(R.id.image)
            val myTitle = row.findViewById<TextView>(R.id.textView1)
            val myDescription = row.findViewById<TextView>(R.id.textView2)
            // now set our resources on views
            images.setImageResource(rImgs[position])
            myTitle.text = rTitle[position]
            myDescription.text = rDescription[position]
            return row
        }

    }
}