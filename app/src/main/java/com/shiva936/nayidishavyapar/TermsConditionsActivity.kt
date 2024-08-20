package com.shiva936.nayidishavyapar

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.shiva936.nayidishavyapar.databinding.ActivityTermsConditionsBinding

class TermsConditionsActivity : ComponentActivity() {
    private lateinit var binding: ActivityTermsConditionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTermsConditionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backArrow.setOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }
        binding.ndy.setOnClickListener{
            val childIntent = Intent(this@TermsConditionsActivity,MainMenuActivity::class.java)
            startActivity(childIntent)
        }
    }
}
