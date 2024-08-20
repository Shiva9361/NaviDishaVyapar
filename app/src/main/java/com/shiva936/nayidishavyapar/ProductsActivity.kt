package com.shiva936.nayidishavyapar

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.TypedValue.COMPLEX_UNIT_DIP
import android.util.TypedValue.applyDimension
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.shiva936.nayidishavyapar.databinding.ActivityYourProductsBinding

class ProductsActivity : ComponentActivity() {
    private lateinit var productsBinding: ActivityYourProductsBinding
    private val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    private val databaseReference = database.reference
    private val auth = FirebaseAuth.getInstance()
    private val storage = FirebaseStorage.getInstance()
    private var storageRef = storage.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productsBinding = ActivityYourProductsBinding.inflate(layoutInflater)
        val view = productsBinding.root
        setContentView(view)
        loadMaterials()
        productsBinding.backButton.setOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }

        productsBinding.backButton.setOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }
    }
    private fun loadMaterials() {
        val materials = mutableListOf<String>()
        materials.add("Agricultural")
        materials.add("Food")
        materials.add("Health")
        materials.add("Mining")
        materials.add("Office")
        materials.add("Auto")
        materials.add("Manufacturing")
        materials.add("Retail")
        materials.add("Hospitality")
        materials.add("Other")

        productsBinding.searchResults.visibility = View.VISIBLE
        for (material in materials) {
            databaseReference.child("Categories").child(material).orderByChild("user").equalTo(auth.currentUser!!.uid)
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
//                        if(snapshot.exists()){
//                            productsBinding.searchResults.removeAllViews()
//                        }
                        for (materialSnapshot in snapshot.children) {
                            val materialData = materialSnapshot.getValue(MaterialDataClass::class.java)
                            addMaterialView(materialData!!,"Categories/${material}/${materialSnapshot.key!!}", material)
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        //
                    }
                })
        }
    }
    private fun addMaterialView(material: MaterialDataClass,path: String,category:String) {
        val inflater = LayoutInflater.from(this)
        val view = inflater.inflate(R.layout.your_product, productsBinding.searchResults, false)


        view.findViewById<TextView>(R.id.material_name).text = material.name
        view.findViewById<TextView>(R.id.material_cost).text = "₹ "+material.cost.toString()
        view.findViewById<TextView>(R.id.material_quantity).text = material.quantity+ " Ton(s)"

        val imageFrame = view.findViewById<ImageView>(R.id.item_image)
        val image = material.images!!.entries.first().key
        val width = dpToPx(200f)
        val height = dpToPx(100f)
        storageRef.child("images/${image}.jpg").downloadUrl.addOnSuccessListener { uri ->
            Glide.with(this)
                .load(uri).placeholder(R.drawable.agriculture_waste).override(width,height)
                .into(imageFrame)
            println(uri)
            imageFrame.visibility = View.VISIBLE
            println("done")
        }.addOnFailureListener {
            // Handle errors
            Toast.makeText(applicationContext, "Some error occurred", Toast.LENGTH_SHORT).show()
        }
        //view.findViewById<TextView>(R.id.distance).text = material.distance
        view.visibility = View.VISIBLE
        view.setOnClickListener{
            val childIntent = Intent(this@ProductsActivity,ProductDetailedViewActivity::class.java)
            childIntent.putExtra("path",path)
            childIntent.putExtra("category",category)
            startActivity(childIntent)
        }

        productsBinding.searchResults.addView(view)
    }
    private fun dpToPx(sp: Float): Int {
        return applyDimension(
            COMPLEX_UNIT_DIP,
            sp,
            resources.displayMetrics
        ).toInt()
    }
}