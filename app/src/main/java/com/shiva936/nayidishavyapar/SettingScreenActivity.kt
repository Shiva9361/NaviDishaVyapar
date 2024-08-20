package com.shiva936.nayidishavyapar

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.google.firebase.auth.FirebaseAuth
import com.shiva936.nayidishavyapar.databinding.ActivitySettingsScreenBinding

class SettingScreenActivity : ComponentActivity() {
    private lateinit var settingScreenBinding: ActivitySettingsScreenBinding
    private var auth = FirebaseAuth.getInstance()

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
        settingScreenBinding.signOut.setOnClickListener {
            auth.signOut()
            val childIntent = Intent(this@SettingScreenActivity,SplashScreenActivity::class.java)
            startActivity(childIntent)
            finish()
        }

        settingScreenBinding.shareTheApp.setOnClickListener {
            val applicationNameId: Int = applicationInfo.labelRes
            val appPackageName: String = packageName
            val i = Intent(Intent.ACTION_SEND)
            i.setType("text/plain")
            i.putExtra(Intent.EXTRA_SUBJECT, getString(applicationNameId))
            val text = "Share ndy, "
            val link = "https://play.google.com/store/apps/details?id=$appPackageName"
            i.putExtra(Intent.EXTRA_TEXT, "$text $link")
            startActivity(Intent.createChooser(i, "Share link:"))
        }
    }
}
