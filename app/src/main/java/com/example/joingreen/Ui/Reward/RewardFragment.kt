package com.example.joingreen.Ui.Reward


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.joingreen.R

class RewardFragment : Fragment() {

    private lateinit var rewardviewModel: RewardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rewardviewModel =
            ViewModelProviders.of(this).get(RewardViewModel::class.java)
        val root = inflater.inflate(R.layout.reward, container, false)
        val textView: TextView = root.findViewById(R.id.reward)
        rewardviewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}