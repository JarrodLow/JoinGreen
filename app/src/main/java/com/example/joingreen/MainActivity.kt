package com.example.joingreen

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    //Declaration for other views
    //User db email and pass
    private var email: String? = null
    private var password: String? = null

    //other buttons and etc
    private var inputEmail :EditText? =null
    private var inputPass : EditText?=null
    private var loginbtn : Button? =null
    private var forgotpass :TextView? =null
    private var register : TextView? =null

    //progress dialog to show login succcess or not
    private var progressbar :ProgressDialog? =null

    //Firebase auth
    private var auth : FirebaseAuth? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        auth = FirebaseAuth.getInstance()

        initialse()


    }
    override fun onStart()
    {
        super.onStart()
        val currentUser = auth!!.currentUser
        updateUI(currentUser)
    }
    override fun onBackPressed() {
        moveTaskToBack(false)

    }

    private fun initialse()
    {
        forgotpass = findViewById<View>(R.id.forgetPassword) as TextView
        inputEmail = findViewById<View>(R.id.userName) as EditText
        inputPass = findViewById<View>(R.id.password) as EditText
        loginbtn = findViewById<View>(R.id.LogIn) as Button
        register = findViewById<View>(R.id.RegisterText) as TextView
        progressbar = ProgressDialog(this)




        //check null
        forgotpass!!.setOnClickListener { startActivity(Intent(this, ForgotPassword::class.java)) }

        loginbtn!!.setOnClickListener { loginUser() }

        register!!.setOnClickListener { startActivity(Intent(this, RegisterUser::class.java)) }
    }
    private fun loginUser(){
        email = inputEmail?.text.toString()
        password = inputPass?.text.toString()

        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            progressbar!!.setMessage("Logging In...")
            progressbar!!.show()

            this.auth!!.signInWithEmailAndPassword(email!!, password!!).addOnCompleteListener(this, OnCompleteListener<AuthResult> { task ->
                progressbar!!.hide()
                if (task.isSuccessful) {
                    Toast.makeText(this, "Welcome to JoinGreen!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, AfterLogin::class.java))
                } else {
                    Toast.makeText(this, "Error email and password", Toast.LENGTH_SHORT).show()
                }
            })
        } else {
            Toast.makeText(this, "Please fill up the credentials", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if(currentUser!=null)
        {
            startActivity(Intent(this, AfterLogin::class.java))
            finish()
        }

    }
}
