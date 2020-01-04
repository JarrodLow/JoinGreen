package com.example.joingreen.Ui.User


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.joingreen.R

class UserFragment : Fragment() {

    private lateinit var userviewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userviewModel =
            ViewModelProviders.of(this).get(UserViewModel::class.java)
        val root = inflater.inflate(R.layout.login, container, false)
        val textView: TextView = root.findViewById(R.id.logoText)
        userviewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}