package com.shiva936.nayidishavyapar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.shiva936.nayidishavyapar.databinding.ActivityMaterialActionBinding

class MaterialActionActivity : ComponentActivity() {
    private lateinit var materialActionBinding: ActivityMaterialActionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        materialActionBinding = ActivityMaterialActionBinding.inflate(layoutInflater)
        val view = materialActionBinding.root
        setContentView(view)
    }
}