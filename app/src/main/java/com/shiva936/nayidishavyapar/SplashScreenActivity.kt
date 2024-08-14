package com.shiva936.nayidishavyapar

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import com.google.firebase.auth.FirebaseAuth

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : ComponentActivity() {
    private val auth : FirebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler(Looper.getMainLooper()).postDelayed(
            {
                var intent = Intent(this@SplashScreenActivity,MaterialActionActivity::class.java)
                if (auth.currentUser != null) {
                    intent = Intent(this@SplashScreenActivity, MainMenuActivity::class.java)
                }
                startActivity(intent)
                finish()
            },2000
        )
    }
}
