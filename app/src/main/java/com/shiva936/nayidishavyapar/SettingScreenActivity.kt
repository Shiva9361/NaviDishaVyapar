package com.shiva936.nayidishavyapar
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.shiva936.nayidishavyapar.databinding.ActivitySettingsScreenBinding

class SettingScreenActivity : ComponentActivity() {
    private lateinit var settingScreenBinding: ActivitySettingsScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        settingScreenBinding = ActivitySettingsScreenBinding.inflate(layoutInflater)
        val view = settingScreenBinding.root
        setContentView(view)
        settingScreenBinding.backButton.setOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }
    }
}