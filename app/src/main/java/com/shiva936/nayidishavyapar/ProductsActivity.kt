package com.shiva936.nayidishavyapar

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.shiva936.nayidishavyapar.databinding.ActivityYourProductsBinding

class ProductsActivity : ComponentActivity() {
    private lateinit var productsBinding: ActivityYourProductsBinding
    private val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    private val databaseReference = database.reference
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productsBinding = ActivityYourProductsBinding.inflate(layoutInflater)
        val view = productsBinding.root
        setContentView(view)
        loadMaterials()

    }
    private fun loadMaterials() {
        val materials = mutableListOf<String>()
        materials.add("Agricultural")

        productsBinding.searchResults.visibility = View.VISIBLE
        for (material in materials) {
            databaseReference.child("Categories").child(material).orderByChild("user").equalTo(auth.currentUser!!.uid)
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if(snapshot.exists()){
                            productsBinding.searchResults.removeAllViews()
                        }
                        for (materialSnapshot in snapshot.children) {
                            val materialData = materialSnapshot.getValue(MaterialDataClass::class.java)
                            addMaterialView(materialData!!)
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        //
                    }
                })
        }
    }
    private fun addMaterialView(material: MaterialDataClass) {
        val inflater = LayoutInflater.from(this)
        val view = inflater.inflate(R.layout.search_result_item, productsBinding.searchResults, false)


        view.findViewById<TextView>(R.id.material_name).text = material.name
        view.findViewById<TextView>(R.id.material_cost).text = "₹ "+material.cost.toString()
        view.findViewById<TextView>(R.id.material_quantity).text = material.quantity+ " Ton(s)"

        //view.findViewById<TextView>(R.id.distance).text = material.distance
        view.visibility = View.VISIBLE
        view.setOnClickListener{
            intent = Intent(this@ProductsActivity,ProductDetailedViewActivity::class.java)
            startActivity(intent)
        }

        productsBinding.searchResults.addView(view)
    }
}