package com.shiva936.nayidishavyapar

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.shiva936.nayidishavyapar.databinding.ActivitySettingsScreenBinding

class SettingScreenActivity : ComponentActivity() {
    private lateinit var settingScreenBinding: ActivitySettingsScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        settingScreenBinding = ActivitySettingsScreenBinding.inflate(layoutInflater)
        setContentView(settingScreenBinding.root)

        settingScreenBinding.backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        settingScreenBinding.personalize.setOnClickListener {
            // Navigate to Manage Notifications
            val intent = Intent(this, ManageNotificationsActivity::class.java)
            startActivity(intent)
        }

        settingScreenBinding.clearHistory.setOnClickListener {
            // Navigate to Clear History
            val intent = Intent(this, ClearHistoryActivity::class.java)
            startActivity(intent)
        }

        settingScreenBinding.tC.setOnClickListener {
            // Navigate to Terms and Conditions
            val intent = Intent(this, TermsConditionsActivity::class.java)
            startActivity(intent)
        }

        settingScreenBinding.privacyPolicy.setOnClickListener {
            // Navigate to Privacy Policy
            val intent = Intent(this, PrivacyPolicyActivity::class.java)
            startActivity(intent)
        }

        settingScreenBinding.aboutUs.setOnClickListener {
            // Navigate to About Us
            val intent = Intent(this, AboutUsActivity::class.java)
            startActivity(intent)
        }

        settingScreenBinding.contactUs.setOnClickListener {
            // Navigate to Contact Us
            val intent = Intent(this, ContactUsActivity::class.java)
            startActivity(intent)
        }

//        settingScreenBinding.shareTheApp.setOnClickListener {
//            // Navigate to Share the App
//            val intent = Intent(this, ShareTheAppActivity::class.java)
//            startActivity(intent)
//        }
    }
}
