package com.shiva936.nayidishavyapar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.shiva936.nayidishavyapar.databinding.ActivityYourProductDetailedViewBinding

class ProductDetailedViewOwnerActivity : ComponentActivity() {
    private lateinit var productDetailedBinding : ActivityYourProductDetailedViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productDetailedBinding = ActivityYourProductDetailedViewBinding.inflate(layoutInflater)
        val view = productDetailedBinding.root
        setContentView(view)

    }
}