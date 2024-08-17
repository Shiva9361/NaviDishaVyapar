package com.shiva936.nayidishavyapar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import com.shiva936.nayidishavyapar.databinding.ActivityDetailedItemResultBinding
import com.shiva936.nayidishavyapar.databinding.ActivityYourProductDetailedViewBinding

class ProductDetailedViewActivity : ComponentActivity() {
    private lateinit var productDetailedViewBinding: ActivityDetailedItemResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productDetailedViewBinding = ActivityDetailedItemResultBinding.inflate(layoutInflater)
        val view = productDetailedViewBinding.root
        setContentView(view)
    }
}