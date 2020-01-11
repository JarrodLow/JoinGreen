package com.example.joingreen.ui.event


import android.os.Bundle
import android.view.*
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.joingreen.R


class EventFragment : Fragment() {

    private lateinit var eventviewModel: EventViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        eventviewModel =
            ViewModelProviders.of(this).get(EventViewModel::class.java)
        val root = inflater.inflate(R.layout.event, container, false)
        val textView: ListView = root.findViewById(R.id.eventListView)
       /* eventviewModel.text.observe(this, Observer {
            textView.= it
        })*/
        return root
    }

}