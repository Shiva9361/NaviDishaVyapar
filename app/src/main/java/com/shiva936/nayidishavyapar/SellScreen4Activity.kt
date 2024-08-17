package com.shiva936.nayidishavyapar

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.shiva936.nayidishavyapar.databinding.ActivitySellScreen4Binding

class SellScreen4Activity : ComponentActivity() {
    private lateinit var sellScreen4Binding: ActivitySellScreen4Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sellScreen4Binding = ActivitySellScreen4Binding.inflate(layoutInflater)
        val view = sellScreen4Binding.root
        setContentView(view)
    }
}