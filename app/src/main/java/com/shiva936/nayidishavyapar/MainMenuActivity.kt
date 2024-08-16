package com.shiva936.nayidishavyapar

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import com.shiva936.nayidishavyapar.databinding.ActivityMainMenuBinding

class MainMenuActivity : ComponentActivity() {
    private lateinit var mainMenuBinding: ActivityMainMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainMenuBinding = ActivityMainMenuBinding.inflate(layoutInflater)
        val view = mainMenuBinding.root
        setContentView(view)
        mainMenuBinding.Filter.setOnClickListener{
            intent = Intent(this@MainMenuActivity,SearchScreenActivity::class.java)
            startActivity(intent)
        }
        mainMenuBinding.searchMaterial.setOnClickListener{
            intent = Intent(this@MainMenuActivity,SearchScreenActivity::class.java)
            startActivity(intent)
        }
    }
}