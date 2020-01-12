package com.example.joingreen.ui.event


import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.joingreen.R
import com.example.joingreen.rewardList
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.reward.*
import kotlinx.android.synthetic.main.reward_home.*


class RewardFragment : Fragment() {
    //firebase
    private var auth: FirebaseAuth? = null
    private lateinit var rewardhomeviewModel: RewardHomeViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        rewardhomeviewModel =
            ViewModelProviders.of(this).get(RewardHomeViewModel::class.java)

        var rewardPoint:Int=0

        var database : DatabaseReference = FirebaseDatabase.getInstance().reference.child("/users")
        auth = FirebaseAuth.getInstance()
        val User = auth!!.currentUser
        val UserReference = database!!.child(User!!.uid)
        val root = inflater.inflate(R.layout.reward_home, container, false)
        val txtrewardPointHome:TextView=root.findViewById(R.id.txtrewardPointHome)

        UserReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val holder = snapshot.child("creditpoint").value as String
                rewardPoint = Integer.parseInt(holder)
                txtrewardPointHome.setText(holder)

            }
            override fun onCancelled(databaseError: DatabaseError) {}
        })

          //  rewardhomeviewModel!!.setrewardPoints(rewardPoint)




        val btnClaimReward: TextView = root.findViewById(R.id.btnClaimReward)
        btnClaimReward.setOnClickListener {
            val intent = Intent(context, rewardList::class.java)

            startActivity(intent)
        }

        return root
    }


}