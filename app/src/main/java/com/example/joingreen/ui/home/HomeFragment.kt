package com.example.joingreen.ui.home

import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.joingreen.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.address
import kotlinx.android.synthetic.main.fragment_home.userName
import kotlinx.android.synthetic.main.signup.*
import java.util.*

class HomeFragment : Fragment() {

    //initialise firebase references
    private var databaseReference: DatabaseReference? = null
    private var database: FirebaseDatabase? = null
    private var auth: FirebaseAuth? = null

    private lateinit var homeViewModel: HomeViewModel

    private val currentUser = FirebaseAuth.getInstance().currentUser

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        retrieveUserProfile()



        saveProfile.setOnClickListener {
            val password = profilePass.text.toString().trim()

            if(password.isEmpty() || password.length < 6){
                profilePass.error = "At least 6 character"
                profilePass.requestFocus()
                return@setOnClickListener
            }


            currentUser?.let{user->
                user.updatePassword(password).addOnCompleteListener { task->
                    if(task.isSuccessful){
                        Toast.makeText(this.activity, "Password changed successfully.", Toast.LENGTH_SHORT).show()
                        }else{
                        Toast.makeText(this.activity, "Password changed failed.", Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }

    }


    private fun retrieveUserProfile()
    {
        database = FirebaseDatabase.getInstance()
        databaseReference = database!!.reference!!.child(("/users"))

        //retrieve current user data
        auth = FirebaseAuth.getInstance()

        val User = auth!!.currentUser
        val UserReferences = databaseReference!!.child((User!!.uid))

        UserReferences.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val name = snapshot.child("userName").value as String
                userName.setText(name)
                val url = snapshot.child("profileImageUrl").value as String
                Glide.with(this@HomeFragment).load(url).into(profilePic)


                val rp = snapshot.child("creditpoint").value as String
                creditpoint.setText(rp)

                val addre = snapshot.child("address").value as String
                address.setText(addre)

            }
            override fun onCancelled(databaseError: DatabaseError) {}
        })

        val email = User.email as String
        profileEmail.setText(email)

    }


}