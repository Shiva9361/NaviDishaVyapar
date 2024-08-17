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
        loginBinding = ActivityLoginScreenBinding.inflate(layoutInflater)
        val view = loginBinding.root
        setContentView(view)


        loginBinding.RegisterWithUs.setOnClickListener {

            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        loginBinding.Next.setOnClickListener{
            val email = loginBinding.EmailField.text.toString()
            val password = loginBinding.PasswordField.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty() ){
                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener{ task->
                    if (task.isSuccessful){
                        var intent = Intent(this@LoginActivity, MainMenuActivity::class.java)
                        startActivity(intent)
                        finish()
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