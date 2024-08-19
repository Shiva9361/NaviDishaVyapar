package com.shiva936.nayidishavyapar

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.shiva936.nayidishavyapar.databinding.ActivityManageNotificationsBinding

class ManageNotificationsActivity : ComponentActivity() {
    private lateinit var binding: ActivityManageNotificationsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManageNotificationsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
