package com.shiva936.nayidishavyapar

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.shiva936.nayidishavyapar.databinding.ActivityAboutUsBinding

class AboutUsActivity : ComponentActivity() {
    private lateinit var binding: ActivityAboutUsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutUsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
