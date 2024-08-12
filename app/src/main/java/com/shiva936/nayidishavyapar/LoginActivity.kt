package com.shiva936.nayidishavyapar

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import com.google.firebase.auth.FirebaseAuth
import com.shiva936.nayidishavyapar.databinding.ActivityLoginBinding

/**
 * Login Activity, Takes care of login
 * Uses firebase Auth in backend
 */
//class LoginActivity : ComponentActivity() {
//    private lateinit var loginBinding: ActivityLoginBinding
//    private val auth : FirebaseAuth = FirebaseAuth.getInstance()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
//        val view = loginBinding.root
//        enableEdgeToEdge()
//        setContentView(view)
//        loginBinding.login.setOnClickListener {
//            auth.signInWithEmailAndPassword("cs22b057@iittp.ac.in", "Not my password")
//                .addOnCompleteListener { task ->
//                    if (task.isSuccessful) {
//                        Toast.makeText(
//                            applicationContext,
//                            auth.currentUser?.uid,
//                            Toast.LENGTH_SHORT
//                        )
//                            .show()
//                        println("User ID: ${auth.currentUser?.uid}")
//                    }
//
//                }
//        }
//    }
//}


class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen) // Set the layout to activity_login_screen.xml
        // Find the button by its ID
        val registerButton: Button = this.findViewById(R.id.register_with_us)

        // Set up the click listener
        registerButton.setOnClickListener {
            // Create an Intent to start the SignupActivity
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}