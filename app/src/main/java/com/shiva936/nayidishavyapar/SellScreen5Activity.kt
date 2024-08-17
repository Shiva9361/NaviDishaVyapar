package com.shiva936.nayidishavyapar

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.shiva936.nayidishavyapar.databinding.ActivitySellScreen5Binding

class SellScreen5Activity : ComponentActivity() {
    private lateinit var sellScreen5Binding: ActivitySellScreen5Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sellScreen5Binding = ActivitySellScreen5Binding.inflate(layoutInflater)
        val view = sellScreen5Binding.root
        setContentView(view)
    }
}