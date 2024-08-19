package com.shiva936.nayidishavyapar

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.shiva936.nayidishavyapar.databinding.ActivityClearHistoryBinding

class ClearHistoryActivity : ComponentActivity() {
    private lateinit var binding: ActivityClearHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClearHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
