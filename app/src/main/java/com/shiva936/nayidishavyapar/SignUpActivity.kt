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
    private val userReference = database.reference.child("user_data")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signupScreenBinding = ActivitySignupScreenBinding.inflate(layoutInflater)
        val view = signupScreenBinding.root
        val roles = listOf(signupScreenBinding.BuyerButton,signupScreenBinding.AgentButton,signupScreenBinding.SellerButton)
        selectedRole = signupScreenBinding.BuyerButton.text.toString()
        signupScreenBinding.BuyerButton.setBackgroundColor(Color.RED)

        for (type in roles){
            type.setOnClickListener{
                switchSelectedType(type,roles)
            }
        }

        setContentView(view)
        enableEdgeToEdge()

        signupScreenBinding.Back.setOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }

        signupScreenBinding.NextButton.setOnClickListener{
            val name = signupScreenBinding.NameField.text.toString()
            val email = signupScreenBinding.EmailField.text.toString()
            val password = signupScreenBinding.PasswordField.text.toString()
            if (name.isEmpty() || email.isEmpty() || password.isEmpty()){
                Toast.makeText(applicationContext, "Please fill all details", Toast.LENGTH_SHORT).show()
            }
            else{
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{ task ->
                    if (task.isSuccessful) {
                        userReference.child("${auth.currentUser?.uid}").child("role").setValue(selectedRole)
                        userReference.child("${auth.currentUser?.uid}").child("name").setValue(name)
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