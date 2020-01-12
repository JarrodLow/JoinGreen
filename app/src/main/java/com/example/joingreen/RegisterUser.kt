package com.example.joingreen

import android.app.Activity

import android.content.Intent

import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.signup.*
import java.io.IOException
import java.util.*

class RegisterUser : AppCompatActivity() {






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)
        SignUp.setOnClickListener {
            performRegister()
        }

        cancel.setOnClickListener {
            Toast.makeText(this, "Getting image", Toast.LENGTH_SHORT).show()

            // launch the login activity somehow
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        Retrieveimg.setOnClickListener {
            Toast.makeText(this, "Displaying Images", Toast.LENGTH_SHORT).show()


            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)

        }

    }
    private var filepath: Uri? = null
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null)
        {
            filepath = data.data

            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filepath)

            profileAddPic.setImageBitmap(bitmap)

            Retrieveimg.alpha = 0f
        }
    }
    private fun performRegister() {
        val name = userName.text.toString().trim()
        val id = email.text.toString().trim()
        val pass = password.text.toString().trim()
        val addre = address.text.toString().trim()


        if (name.isEmpty()) {
            userName.error ="Please enter name"
            return
        }
        if (id.isEmpty()) {
            email.error ="Please enter Email"
            return
        }
        if (pass.isEmpty()) {
            password.error ="Please enter password"
            return
        }

        if (addre.isEmpty()) {
            address.error ="Please enter Address"
            return
        }


        Toast.makeText(this, "Creating user....", Toast.LENGTH_SHORT).show()


        // Firebase Authentication to create a user with email and password
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(id,pass)
            .addOnCompleteListener {
                if (!it.isSuccessful) return@addOnCompleteListener

                // else if successful
                Toast.makeText(this, "Uploading to Auth", Toast.LENGTH_SHORT).show()

                uploadImage()
            }
            .addOnFailureListener{

                Toast.makeText(this, "Failed to create user: ${it.message}", Toast.LENGTH_SHORT).show()
            }

    }
    private fun uploadImage() {
        if (filepath != null) {
            val filename = UUID.randomUUID().toString()
            val ref = FirebaseStorage.getInstance().getReference("/images/$filename")
            ref.putFile(filepath!!)
                .addOnSuccessListener {
                    Toast.makeText(this, "Uploading Image...............", Toast.LENGTH_SHORT).show()


                    ref.downloadUrl.addOnSuccessListener {
                        Toast.makeText(this, "Done uploading Image", Toast.LENGTH_SHORT).show()


                        saveUserToFirebaseDatabase(it.toString())

                    }
                        .addOnFailureListener {
                            Toast.makeText(this, "Failed upload Image", Toast.LENGTH_SHORT).show()

                        }

                }
        }
    }
    private fun saveUserToFirebaseDatabase(profileImageUrl: String) {
        val uid = FirebaseAuth.getInstance().uid ?: ""
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")

        val user = User(uid,profileImageUrl,userName.text.toString(),email.text.toString(),address.text.toString(),creditpoint = "0")

        ref.setValue(user)
            .addOnSuccessListener {
                Toast.makeText(this, "Register Success", Toast.LENGTH_SHORT).show()


            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()

            }
    }

}
class User(val uid: String, val profileImageUrl: String,val userName:String,val LoginId: String, val address :String, val creditpoint: String)
