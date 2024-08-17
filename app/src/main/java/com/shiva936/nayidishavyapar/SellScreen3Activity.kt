package com.shiva936.nayidishavyapar

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.shiva936.nayidishavyapar.databinding.ActivitySellScreen3Binding

class SellScreen3Activity : ComponentActivity() {
    private lateinit var sellScreen3Binding: ActivitySellScreen3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sellScreen3Binding = ActivitySellScreen3Binding.inflate(layoutInflater)
        val view = sellScreen3Binding.root
        setContentView(view)
    }
}