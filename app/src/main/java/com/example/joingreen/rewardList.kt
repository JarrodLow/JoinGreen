package com.example.joingreen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class rewardList : AppCompatActivity() {

    var mTitle =
        arrayOf("Facebook", "Whatsapp", "Twitter", "Instagram", "Youtube")
    var mPoints = arrayOf("100", "200", "300", "400", "500")
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
        setContentView(R.layout.reward)
        val listView:ListView = findViewById(R.id.rewardListView)
        // now create an adapter class
        mButton = findViewById(R.id.retrieveBtn)

        val adapter = MyAdapter(this, mTitle, mPoints, images, mButton)
        listView.setAdapter(adapter)

        // so item click is done now check list view
    }


    internal inner class MyAdapter(
        context: Context,
        var rTitle: Array<String>,
        var rDescription: Array<String>,
        var rImgs: IntArray,
        var rButton: Button?
    ) : ArrayAdapter<String?>(context, R.layout.reward_list, R.id.rewardName, rTitle) {
        override fun getView(
            position: Int,
            convertView: View?,
            parent: ViewGroup
        ): View {
            val layoutInflater =
                applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val row = layoutInflater.inflate(R.layout.reward_list, parent, false)
            val images =
                row.findViewById<ImageView>(R.id.rewardImageView)
            val myTitle = row.findViewById<TextView>(R.id.rewardName)
            val myDescription = row.findViewById<TextView>(R.id.rewardPoints)
            val myButton =
                row.findViewById<Button>(R.id.retrieveBtn)
            // now set our resources on views
            images.setImageResource(rImgs[position])
            myTitle.text = rTitle[position]
            myDescription.text = rDescription[position]
            myButton.setOnClickListener {
                var rewardPoint:Int= Integer.parseInt(mPoints[position])
                val ttlRewardPoint:TextView=findViewById(R.id.totalReward)
                var str:String=ttlRewardPoint.getText().toString()
                var totalRewardPoint:Int=Integer.parseInt(str)
                if(rewardPoint.compareTo(totalRewardPoint)<0)
                {
                    totalRewardPoint=totalRewardPoint-rewardPoint
                    ttlRewardPoint.setText(totalRewardPoint.toString())
                    Toast.makeText(this@rewardList, mTitle[position]+" reward claimed", Toast.LENGTH_SHORT)
                        .show()
                }

            }
            return row
        }

    }
}