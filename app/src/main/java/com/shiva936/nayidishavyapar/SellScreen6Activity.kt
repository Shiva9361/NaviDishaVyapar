package com.shiva936.nayidishavyapar

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.shiva936.nayidishavyapar.databinding.ActivitySellScreen6Binding

class SellScreen6Activity : ComponentActivity() {
    private lateinit var sellScreen6Binding: ActivitySellScreen6Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sellScreen6Binding = ActivitySellScreen6Binding.inflate(layoutInflater)
        val view = sellScreen6Binding.root
        setContentView(view)
    }
}