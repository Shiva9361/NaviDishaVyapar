package com.shiva936.nayidishavyapar

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity

class SplashScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler(Looper.getMainLooper()).postDelayed(
            {
                val intent = Intent(this@SplashScreenActivity,LoginActivity::class.java)
                startActivity(intent)
                finish()
            },2000
        )
    }
}
