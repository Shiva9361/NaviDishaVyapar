package com.shiva936.nayidishavyapar

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import android.graphics.Color
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.database.FirebaseDatabase
import com.shiva936.nayidishavyapar.databinding.ActivitySignupScreenBinding


class SignUpActivity : ComponentActivity() {
    private lateinit var signupScreenBinding: ActivitySignupScreenBinding
    private lateinit var selectedRole: String
    private val auth : FirebaseAuth = FirebaseAuth.getInstance()
    private val database :FirebaseDatabase = FirebaseDatabase.getInstance()
    private val userref = database.reference.child("user_data")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signupScreenBinding = ActivitySignupScreenBinding.inflate(layoutInflater)
        val view = signupScreenBinding.root
        val roles = listOf(signupScreenBinding.Buyer,signupScreenBinding.Agent,signupScreenBinding.Seller)
        selectedRole = signupScreenBinding.Buyer.text.toString()
        signupScreenBinding.Buyer.setBackgroundColor(Color.RED)

        for (type in roles){
            type.setOnClickListener{
                switchSelectedType(type,roles)
            }
        }

        setContentView(view)
        enableEdgeToEdge()

        signupScreenBinding.next.setOnClickListener{
            val name = signupScreenBinding.Name.text.toString()
            val email = signupScreenBinding.Email.text.toString()
            val password = signupScreenBinding.Password.text.toString()
            if (name.isEmpty() || email.isEmpty() || password.isEmpty()){
                Toast.makeText(applicationContext, "Please fill all details", Toast.LENGTH_SHORT).show()
            }
            else{
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{ task ->
                    if (task.isSuccessful) {
                        userref.child("${auth.currentUser?.uid}").child("role").setValue(selectedRole)
                        userref.child("${auth.currentUser?.uid}").child("name").setValue(name)
                    }
                    else{
                        if (task.exception is FirebaseAuthUserCollisionException){
                            Toast.makeText(applicationContext, "Email is already registered", Toast.LENGTH_SHORT).show()
                        }
                        else{
                            Toast.makeText(applicationContext, "${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }

    }
    private fun switchSelectedType(role:Button,roles:List<Button>){
        for (type in roles){
            type.setBackgroundColor(Color.WHITE)
        }
        role.setBackgroundColor(Color.RED)
        selectedRole = role.text.toString()
    }
}