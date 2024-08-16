package com.shiva936.nayidishavyapar

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.shiva936.nayidishavyapar.databinding.ActivitySearchResultBinding

class SearchResultActivity : ComponentActivity() {
    private lateinit var searchResultBinding: ActivitySearchResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchResultBinding = ActivitySearchResultBinding.inflate(layoutInflater)
        val view = searchResultBinding.root
        setContentView(view)
        searchResultBinding.editSearch.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}