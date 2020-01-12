package com.example.joingreen.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.joingreen.R
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    //initialise firebase references
    private var databaseReference: DatabaseReference? = null
    private var database: FirebaseDatabase? = null
    private var auth: FirebaseAuth? = null

    companion object {
        fun newInstance() = HomeFragment()
    }
    private val currentUser = FirebaseAuth.getInstance().currentUser
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
            val password = changePsw.text.toString().trim()

            if(password.isEmpty() || password.length < 6){
                changePsw.error = "At least 6 character"
                changePsw.requestFocus()
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
    private fun retrieveUserProfile() {

            database = FirebaseDatabase.getInstance()
            databaseReference = database!!.reference!!.child("/users")
            auth = FirebaseAuth.getInstance()


            val mUser = auth!!.currentUser
            val mUserReference = databaseReference!!.child(mUser!!.uid)


            mUserReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    val name = snapshot.child("userName").value as String
                    displayName.setText(name)

                    val url = snapshot.child("profileImageUrl").value as String
                    Glide.with(this@HomeFragment).load(url).into(profilePic)


                    val rp = snapshot.child("creditpoint").value as String
                    creditpoint.setText(rp)

                    val addre = snapshot.child("address").value as String
                    displayAddress.setText(addre)

                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })

            val email = mUser.email as String
            profileEmail.setText(email)
        }
    }

