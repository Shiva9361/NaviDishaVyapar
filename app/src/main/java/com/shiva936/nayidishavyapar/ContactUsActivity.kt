package com.shiva936.nayidishavyapar

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.shiva936.nayidishavyapar.databinding.ActivityContactUsBinding

class ContactUsActivity : ComponentActivity() {
    private lateinit var binding: ActivityContactUsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactUsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.ndy.setOnClickListener{
            val childIntent = Intent(this@ContactUsActivity,MainMenuActivity::class.java)
            startActivity(childIntent)
        }
        binding.backArrow.setOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }
    }
}
