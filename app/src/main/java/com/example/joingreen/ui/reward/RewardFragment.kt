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
import kotlinx.android.synthetic.main.reward_home.*


class RewardFragment : Fragment() {

    private lateinit var rewardhomeviewModel: RewardHomeViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        rewardhomeviewModel =
            ViewModelProviders.of(this).get(RewardHomeViewModel::class.java)

            var rewardPoint:Int=10000
            rewardhomeviewModel!!.setrewardPoints(rewardPoint)


        val root = inflater.inflate(R.layout.reward_home, container, false)
        val txtrewardPointHome:TextView=root.findViewById(R.id.txtrewardPointHome)
        txtrewardPointHome.setText(rewardhomeviewModel.toString())
        val textView: TextView = root.findViewById(R.id.txtrewardPointHome)

        rewardhomeviewModel.rewardPoints.observe(this, Observer {
            textView.text = it.toString()
        })
        val btnClaimReward: TextView = root.findViewById(R.id.btnClaimReward)
        btnClaimReward.setOnClickListener {
            val intent = Intent(context, rewardList::class.java)
            intent.putExtra("RewardPoint",rewardPoint)
            startActivity(intent)
        }

        return root
    }

}