package com.shiva936.nayidishavyapar

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : ComponentActivity() {
    private val auth : FirebaseAuth = FirebaseAuth.getInstance()
    private val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    private val userReference = database.reference.child("user_data")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler(Looper.getMainLooper()).postDelayed(
            {
                var intent = Intent(this@SplashScreenActivity, MainMenuActivity::class.java)
                if (auth.currentUser != null) {
                    userReference.child(auth.currentUser!!.uid.toString()).child("locations").get()
                        .addOnCompleteListener { task ->
                            if (!task.isSuccessful || !task.result.exists()) {
                                intent = Intent(
                                    this@SplashScreenActivity,
                                    LocationScreenActivity::class.java
                                )
                            }
                            userReference.child(auth.currentUser!!.uid.toString()).child("material")
                                .get().addOnCompleteListener { task ->
                                if (!task.isSuccessful || !task.result.exists()) {
                                    intent = Intent(
                                        this@SplashScreenActivity,
                                        MaterialTypeActivity::class.java
                                    )
                                }
                                startActivity(intent)
                                finish()
                            }
                        }
                }
                else {
                    auth.signInAnonymously().addOnCompleteListener {
                        intent = Intent(this@SplashScreenActivity, MaterialTypeActivity::class.java)
                    }
                }
            },2000
        )
    }
}
