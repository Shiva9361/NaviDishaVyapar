package com.shiva936.nayidishavyapar

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.snap
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.shiva936.nayidishavyapar.databinding.ActivityLocationScreenBinding
import com.shiva936.nayidishavyapar.databinding.ActivityMaterialTypeBinding

class LocationScreenActivity : ComponentActivity() {

    private lateinit var locationScreenBinding: ActivityLocationScreenBinding
    private val auth : FirebaseAuth = FirebaseAuth.getInstance()
    private val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    private val userReference = database.reference.child("user_data")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        locationScreenBinding= ActivityLocationScreenBinding.inflate(layoutInflater)
        val view = locationScreenBinding.root
        setContentView(view)
        locationScreenBinding.dropDownCities.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedCity = parent.getItemAtPosition(position).toString()
                if (selectedCity != "Select City") {
                    userReference.child("${auth.currentUser?.uid}").child("locations").child(selectedCity).addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            // Check if the snapshot has any child
                            if (!snapshot.exists()) {
                                userReference.child("${auth.currentUser?.uid}").child("locations")
                                    .child(selectedCity).setValue("Dummy")
                            }
                            onBackPressedDispatcher.onBackPressed()
                        }

                        override fun onCancelled(error: DatabaseError) {
                            // Handle possible errors
                        }
                    })
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Wait
            }
        }
        //loadDropDownData()

    }
    private fun loadDropDownData(){

        userReference.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val cities = mutableListOf<String>()
                cities.add("Select an option")
                for (dataSnapshot in snapshot.children){
                    val city = dataSnapshot.getValue(String::class.java)
                    city?.let { cities.add(it) } // Add only if possible
                }
                val adapter = ArrayAdapter(this@LocationScreenActivity,android.R.layout.simple_spinner_item,cities)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                locationScreenBinding.dropDownCities.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "Something Went wrong", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
