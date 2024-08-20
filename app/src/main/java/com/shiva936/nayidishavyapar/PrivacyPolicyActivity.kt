package com.shiva936.nayidishavyapar

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import com.shiva936.nayidishavyapar.databinding.ActivityPrivacyPolicyBinding

class PrivacyPolicyActivity : ComponentActivity() {
    private lateinit var binding: ActivityPrivacyPolicyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrivacyPolicyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.ndy.setOnClickListener{
            val childIntent = Intent(this@PrivacyPolicyActivity,MainMenuActivity::class.java)
            startActivity(childIntent)
        }
        binding.backArrow.setOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }
    }
}
