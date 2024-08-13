package com.shiva936.nayidishavyapar

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import com.google.firebase.auth.FirebaseAuth
import com.shiva936.nayidishavyapar.databinding.ActivityLoginScreenBinding

/**
 * Login Activity, Takes care of login
 * Uses firebase Auth in backend
 */
class LoginActivity : ComponentActivity() {
    private lateinit var loginBinding: ActivityLoginScreenBinding
    private val auth : FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //ActivityNameBinding can be used to get the content from XML
        loginBinding = ActivityLoginScreenBinding.inflate(layoutInflater)
        val view = loginBinding.root
        enableEdgeToEdge()
        setContentView(view)

        // Set up the click listener
        loginBinding.registerWithUs.setOnClickListener {
            // Create an Intent to start the SignupActivity
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        loginBinding.Next.setOnClickListener{
            val email = loginBinding.Email.text.toString()
            val password = loginBinding.Password.text.toString()
            if (email !="" && password !="" ){
                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener{ task->
                    if (task.isSuccessful){
                        Toast.makeText(applicationContext, auth.currentUser?.uid  ,Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Toast.makeText(applicationContext, "Something went wrong!!"  ,Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else{
                Toast.makeText(applicationContext,"Please enter both email and password",Toast.LENGTH_SHORT).show()
            }
        }
    }
}