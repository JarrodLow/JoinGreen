package com.example.joingreen.ui.info


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.google.firebase.database.*


class InfoFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       return inflater.inflate(com.example.joingreen.R.layout.fragment_info, container, false)

    }


}