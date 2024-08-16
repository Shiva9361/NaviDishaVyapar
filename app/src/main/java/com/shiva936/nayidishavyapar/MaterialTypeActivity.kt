package com.shiva936.nayidishavyapar

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.shiva936.nayidishavyapar.databinding.ActivityMaterialTypeBinding

class MaterialTypeActivity : ComponentActivity() {
    private lateinit var materialTypeBinding: ActivityMaterialTypeBinding
    private val auth : FirebaseAuth = FirebaseAuth.getInstance()
    private val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    private val userReference = database.reference.child("user_data")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        materialTypeBinding = ActivityMaterialTypeBinding.inflate(layoutInflater)
        val view = materialTypeBinding.root
        setContentView(view)

        /**
         * Listeners for all buttons
         */
        materialTypeBinding.frameAgriculture.setOnClickListener{
            userReference.child(auth.currentUser!!.uid).child("material").setValue("Agricultural")
            userReference.child(auth.currentUser!!.uid.toString()).child("location").get().addOnCompleteListener { task ->
                if (!task.isSuccessful || !task.result.exists()) {
                    intent = Intent(this@MaterialTypeActivity, LocationScreenActivity::class.java)
                    startActivity(intent)
                }
            }
            intent = Intent(this@MaterialTypeActivity, MainMenuActivity::class.java)
            startActivity(intent)
            finish()
        }
        materialTypeBinding.frameAuto.setOnClickListener{
            userReference.child(auth.currentUser!!.uid).child("material").setValue("Auto")
            userReference.child(auth.currentUser!!.uid.toString()).child("location").get().addOnCompleteListener { task ->
                if (!task.isSuccessful || !task.result.exists()) {
                    intent = Intent(this@MaterialTypeActivity, LocationScreenActivity::class.java)
                    startActivity(intent)
                }
            }
            intent = Intent(this@MaterialTypeActivity, MainMenuActivity::class.java)
            startActivity(intent)
            finish()
        }
        materialTypeBinding.frameFood.setOnClickListener{
            userReference.child(auth.currentUser!!.uid).child("material").setValue("Food")
            userReference.child(auth.currentUser!!.uid.toString()).child("location").get().addOnCompleteListener { task ->
                if (!task.isSuccessful || !task.result.exists()) {
                    intent = Intent(this@MaterialTypeActivity, LocationScreenActivity::class.java)
                    startActivity(intent)
                }
            }
            intent = Intent(this@MaterialTypeActivity, MainMenuActivity::class.java)
            startActivity(intent)
            finish()
        }
        materialTypeBinding.frameOther.setOnClickListener{
            userReference.child(auth.currentUser!!.uid).child("material").setValue("Other")
            userReference.child(auth.currentUser!!.uid.toString()).child("location").get().addOnCompleteListener { task ->
                if (!task.isSuccessful || !task.result.exists()) {
                    intent = Intent(this@MaterialTypeActivity, LocationScreenActivity::class.java)
                    startActivity(intent)
                }
            }
            intent = Intent(this@MaterialTypeActivity, MainMenuActivity::class.java)
            startActivity(intent)
            finish()
        }
        materialTypeBinding.frameHealth.setOnClickListener{
            userReference.child(auth.currentUser!!.uid).child("material").setValue("Health")
            userReference.child(auth.currentUser!!.uid.toString()).child("location").get().addOnCompleteListener { task ->
                if (!task.isSuccessful || !task.result.exists()) {
                    intent = Intent(this@MaterialTypeActivity, LocationScreenActivity::class.java)
                    startActivity(intent)
                }
            }
            intent = Intent(this@MaterialTypeActivity, MainMenuActivity::class.java)
            startActivity(intent)
            finish()
        }
        materialTypeBinding.frameHospitality.setOnClickListener{
            userReference.child(auth.currentUser!!.uid).child("material").setValue("Hospitality")
            userReference.child(auth.currentUser!!.uid.toString()).child("location").get().addOnCompleteListener { task ->
                if (!task.isSuccessful || !task.result.exists()) {
                    intent = Intent(this@MaterialTypeActivity, LocationScreenActivity::class.java)
                    startActivity(intent)
                }
            }
            intent = Intent(this@MaterialTypeActivity, MainMenuActivity::class.java)
            startActivity(intent)
            finish()
        }
        materialTypeBinding.frameManufacturing.setOnClickListener{
            userReference.child(auth.currentUser!!.uid).child("material").setValue("Manufacturing")
            userReference.child(auth.currentUser!!.uid.toString()).child("location").get().addOnCompleteListener { task ->
                if (!task.isSuccessful || !task.result.exists()) {
                    intent = Intent(this@MaterialTypeActivity, LocationScreenActivity::class.java)
                    startActivity(intent)
                }
            }
            intent = Intent(this@MaterialTypeActivity, MainMenuActivity::class.java)
            startActivity(intent)
            finish()
        }
        materialTypeBinding.frameMining.setOnClickListener{
            userReference.child(auth.currentUser!!.uid).child("material").setValue("Mining")
            userReference.child(auth.currentUser!!.uid.toString()).child("location").get().addOnCompleteListener { task ->
                if (!task.isSuccessful || !task.result.exists()) {
                    intent = Intent(this@MaterialTypeActivity, LocationScreenActivity::class.java)
                    startActivity(intent)
                }
            }
            intent = Intent(this@MaterialTypeActivity, MainMenuActivity::class.java)
            startActivity(intent)
            finish()
        }
        materialTypeBinding.frameOffice.setOnClickListener{
            userReference.child(auth.currentUser!!.uid).child("material").setValue("Office")
            userReference.child(auth.currentUser!!.uid.toString()).child("location").get().addOnCompleteListener { task ->
                if (!task.isSuccessful || !task.result.exists()) {
                    intent = Intent(this@MaterialTypeActivity, LocationScreenActivity::class.java)
                    startActivity(intent)
                }
            }
            intent = Intent(this@MaterialTypeActivity, MainMenuActivity::class.java)
            startActivity(intent)
            finish()
        }
        materialTypeBinding.frameRetail.setOnClickListener{
            userReference.child(auth.currentUser!!.uid).child("material").setValue("Retail")
            userReference.child(auth.currentUser!!.uid.toString()).child("location").get().addOnCompleteListener { task ->
                if (!task.isSuccessful || !task.result.exists()) {
                    intent = Intent(this@MaterialTypeActivity, LocationScreenActivity::class.java)
                    startActivity(intent)
                }
            }
            intent = Intent(this@MaterialTypeActivity, MainMenuActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
