package com.shiva936.nayidishavyapar

import android.content.Intent
import android.os.Bundle
import android.util.TypedValue.COMPLEX_UNIT_SP
import android.util.TypedValue.applyDimension
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.ComponentActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.shiva936.nayidishavyapar.databinding.ActivitySearchScreenBinding


class SearchScreenActivity : ComponentActivity() {
    private val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    private val auth : FirebaseAuth = FirebaseAuth.getInstance()
    private val userReference = database.reference.child("user_data")
    private lateinit var searchScreenBinding: ActivitySearchScreenBinding
    private val selectedCities = mutableSetOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchScreenBinding = ActivitySearchScreenBinding.inflate(layoutInflater)

        val view = searchScreenBinding.root
        setContentView(view)
        searchScreenBinding.addMoreButton.setOnClickListener{
            // Can make this smoother afterwards
            intent = Intent(this@SearchScreenActivity,LocationScreenActivity::class.java)
            startActivity(intent)
            finish()
        }
        searchScreenBinding.resetButton.setOnClickListener{
            intent = Intent(this@SearchScreenActivity,SearchScreenActivity::class.java)
            startActivity(intent)
            finish()
        }
        searchScreenBinding.backButton.setOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }

        loadCities()

    }

    private fun loadCities() {
        userReference.child(auth.currentUser!!.uid).child("locations").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                //searchScreenBinding.locationGrid.removeAllViews() // Clear existing buttons
                for (buttonSnapshot in snapshot.children) {
                    val text = buttonSnapshot.key
                    if (text != null) {
                        createButton(text)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle possible errors
            }
        })
    }
    private fun createButton(text: String) {
        val button = Button(this@SearchScreenActivity)
        button.text = text
        selectedCities.add(text)
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,  // Width
            spToPx(40F)
        ).apply {
            setMargins(5, 5, 5, 10) // Set margins
        }
        button.layoutParams = params
        button.visibility = Button.VISIBLE
        button.setTextColor(getColor(R.color.dark_green))
        button.setBackgroundResource(R.drawable.green_background_rounded)
        button.setOnClickListener{
            if (selectedCities.contains(text)){
                selectedCities.remove(text)
                button.setBackgroundResource(R.drawable.baseline_electric_meter_24)

            }
            else{
                selectedCities.add(text)
                button.setBackgroundResource(R.drawable.green_background_rounded)
            }
        }
        searchScreenBinding.locationGrid.addView(button,0)
    }
    private fun spToPx(sp: Float): Int {
        return applyDimension(
            COMPLEX_UNIT_SP,
            sp,
            resources.displayMetrics
        ).toInt()
    }
}