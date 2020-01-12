package com.example.joingreen.ui.LogOut

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.joingreen.AfterLogin
import com.example.joingreen.MainActivity
import com.example.joingreen.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_logout.*


class LogOutFragment : Fragment() {
    companion object{
        fun newInstance() = LogOutFragment()
    }

    lateinit var viewModel : LogOutViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_logout,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        confirmBtn.setOnClickListener {
            FirebaseAuth.getInstance().signOut()

            updateUI()
        }
        cancelBtn.setOnClickListener{
            startActivity(Intent(this.activity, AfterLogin::class.java))
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LogOutViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun updateUI() {
        Toast.makeText(this.activity, "Logout successfully!", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this.activity, MainActivity::class.java))
    }
}