package com.example.joingreen

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.forgetpassword.*

class ForgotPassword : AppCompatActivity() {

    //basic ui
    private var inputEmail : EditText ?= null
    private var submitbtn : Button ?=null
    private var backLogin : Button ?=null

    //firebase
    private var auth : FirebaseAuth ?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgetpassword)
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Forgot Password"

        initialise()
    }

    private fun initialise()
    {
        inputEmail = findViewById<View>(R.id.edit_email) as EditText
        submitbtn = findViewById<View>(R.id.forgotPassword_button) as Button
        backLogin = findViewById<View>(R.id.login_btn) as Button

        auth = FirebaseAuth.getInstance()

        submitbtn!!.setOnClickListener{Resetpassword()}

        backLogin!!.setOnClickListener{startActivity(Intent(this,MainActivity::class.java))}

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun Resetpassword(){
        val email = inputEmail?.text.toString().trim()

        if(!TextUtils.isEmpty(email))
        {
            auth!!.sendPasswordResetEmail(email).addOnCompleteListener{task->
                if(task.isSuccessful)
                {
                    Toast.makeText(this@ForgotPassword,"An email has send to your account for reset password",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,MainActivity::class.java))
                }
                else
                {
                    Toast.makeText(this@ForgotPassword,"Your email was not in our database. Please enter valid email",Toast.LENGTH_SHORT).show()
                }
            }
        }
        else
        {
            edit_email.error="Enter your email"
            return
        }
    }
}