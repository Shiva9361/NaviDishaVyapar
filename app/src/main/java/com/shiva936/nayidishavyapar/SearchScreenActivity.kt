package com.shiva936.nayidishavyapar

import android.content.Intent
import android.os.Bundle
import android.util.TypedValue.COMPLEX_UNIT_SP
import android.util.TypedValue.applyDimension
import android.widget.Button
import android.widget.FrameLayout
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
    private val selectedMaterials = mutableListOf<String>()
    private val selectedQuantity = mutableListOf<String>()
    private lateinit var selectedMethod : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchScreenBinding = ActivitySearchScreenBinding.inflate(layoutInflater)

        val view = searchScreenBinding.root
        setContentView(view)

        val methods = listOf(searchScreenBinding.acquire,searchScreenBinding.lease,searchScreenBinding.request,searchScreenBinding.industrial)
        selectedMethod = methods[0]
        for (method in methods){
            method.setOnClickListener{
                for (method2 in methods){
                    method2.setBackgroundColor(getColor(R.color.light_grey))
                    method2.setTextColor(getColor(R.color.black))
                }
                method.setBackgroundResource(R.drawable.search_screen_button_background_selector)
                method.setTextColor(getColor(R.color.grey))
                selectedMethod = method
            }
        }
        // Initializing here, changing in XML doesn't work :)
        searchScreenBinding.frameOther.alpha = 0.5F
        searchScreenBinding.frameAgriculture.alpha = 0.5F
        searchScreenBinding.frameFood.alpha = 0.5F
        searchScreenBinding.frameHealth.alpha = 0.5F
        searchScreenBinding.frameMining.alpha = 0.5F
        searchScreenBinding.frameOffice.alpha = 0.5F
        searchScreenBinding.frameAuto.alpha = 0.5F
        searchScreenBinding.frameManufacturing.alpha = 0.5F
        searchScreenBinding.frameRetail.alpha = 0.5F
        searchScreenBinding.frameHospitality.alpha = 0.5F


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

        searchScreenBinding.frameAgriculture.setOnClickListener{
            handelMaterialClick("Agriculture",searchScreenBinding.frameAgriculture)
        }
        searchScreenBinding.frameAuto.setOnClickListener{
            handelMaterialClick("Auto",searchScreenBinding.frameAuto)
        }
        searchScreenBinding.frameFood.setOnClickListener{
            handelMaterialClick("Food",searchScreenBinding.frameFood)
        }
        searchScreenBinding.frameHealth.setOnClickListener{
            handelMaterialClick("Health",searchScreenBinding.frameHealth)
        }
        searchScreenBinding.frameOther.setOnClickListener{
            handelMaterialClick("Other",searchScreenBinding.frameOther)
        }
        searchScreenBinding.frameMining.setOnClickListener{
            handelMaterialClick("Mining",searchScreenBinding.frameMining)
        }
        searchScreenBinding.frameHospitality.setOnClickListener{
            handelMaterialClick("Hospitality",searchScreenBinding.frameHospitality)
        }
        searchScreenBinding.frameManufacturing.setOnClickListener{
            handelMaterialClick("Manufacturing",searchScreenBinding.frameManufacturing)
        }
        searchScreenBinding.frameOffice.setOnClickListener{
            handelMaterialClick("Office",searchScreenBinding.frameOffice)
        }
        searchScreenBinding.frameRetail.setOnClickListener{
            handelMaterialClick("Office",searchScreenBinding.frameRetail)
        }

        searchScreenBinding.oneTon.setOnClickListener{
            handleQuantityClick("1",searchScreenBinding.oneTon)
        }
        searchScreenBinding.twoTon.setOnClickListener{
            handleQuantityClick("2",searchScreenBinding.twoTon)
        }
        searchScreenBinding.threeTon.setOnClickListener{
            handleQuantityClick("3",searchScreenBinding.threeTon)
        }
        searchScreenBinding.fourTon.setOnClickListener{
            handleQuantityClick("4",searchScreenBinding.fourTon)
        }
        searchScreenBinding.moreTon.setOnClickListener{
            handleQuantityClick(">4",searchScreenBinding.moreTon)
        }
    }


    private fun handleQuantityClick(s: String, frameLayout: FrameLayout) {
        if (selectedQuantity.contains(s)) {
            frameLayout.setBackgroundResource(R.drawable.grey_rounded_border_background)
        } else {
            frameLayout.setBackgroundResource(R.drawable.baby_pink_background_rounded)
        }
    }

    private fun handelMaterialClick(s: String, frameLayout: FrameLayout) {
        if (selectedMaterials.contains(s)) {
            selectedMaterials.remove(s)
            frameLayout.alpha = 0.5F
        } else {
            selectedMaterials.add(s)
            frameLayout.alpha = 1F
        }
    }

    private fun loadCities() {
        userReference.child(auth.currentUser!!.uid).child("locations").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                //searchScreenBinding.locationGrid.removeAllViews() // Clear existing buttons
                for (buttonSnapshot in snapshot.children) {
                    val text = buttonSnapshot.key
                    if (text != null) {
                        createButton(text,selectedCities)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle possible errors
            }
        })
    }
    private fun createButton(text: String, strings: MutableSet<String>) {
        val button = Button(this@SearchScreenActivity)
        button.text = text
        strings.add(text)
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
            if (strings.contains(text)){
                strings.remove(text)
                button.setBackgroundResource(R.drawable.baseline_electric_meter_24)
                button.setTextColor(getColor(R.color.black))

            }
            else{
                strings.add(text)
                button.setBackgroundResource(R.drawable.green_background_rounded)
                button.setTextColor(getColor(R.color.dark_green))
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