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
import androidx.compose.animation.core.snap
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.shiva936.nayidishavyapar.databinding.ActivitySearchResultBinding

class SearchResultActivity : ComponentActivity() {
    private val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    private val databaseReference = database.reference
    private val auth = FirebaseAuth.getInstance()
    private val storage = FirebaseStorage.getInstance()
    private var storageRef = storage.reference

    private lateinit var searchResultBinding: ActivitySearchResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchResultBinding = ActivitySearchResultBinding.inflate(layoutInflater)
        val view = searchResultBinding.root
        setContentView(view)

        loadMaterials()

        searchResultBinding.editSearch.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        searchResultBinding.backButton.setOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun loadMaterials() {
        val quantities: Set<String> = intent.getStringArrayListExtra("Quantities")!!.toSet()
        val materials = intent.getStringArrayListExtra("Materials")
        val cities: Set<String> = intent.getStringArrayListExtra("Cities")!!.toSet()

        val minPrice = intent.getIntExtra("Min", 0)
        val maxPrice = intent.getIntExtra("Max", 10_00_00_000)
        searchResultBinding.searchResults.visibility = View.VISIBLE
        if (materials != null) {
            for (material in materials) {
                databaseReference.child("Categories").child(material).orderByChild("cost").startAt(minPrice.toDouble())
                    .endAt(maxPrice.toDouble())
                    .addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            if(snapshot.exists()){
                                searchResultBinding.searchResults.removeAllViews()
                            }
                            for (materialSnapshot in snapshot.children) {
                                val materialData =
                                    materialSnapshot.getValue(MaterialDataClass::class.java)
                                if (cities.contains(materialData!!.location) && quantities.contains(materialData.quantity)) {
                                    addMaterialView(materialData)
                                    println(materialData)
                                }

                            }
                        }

                        override fun onCancelled(error: DatabaseError) {
                            // Ignoring
                        }
                    })
            }
        }
    }
    private fun addMaterialView(material: MaterialDataClass) {
        val inflater = LayoutInflater.from(this)
        val view = inflater.inflate(R.layout.search_result_item, searchResultBinding.searchResults, false)


        view.findViewById<TextView>(R.id.material_name).text = material.name
        view.findViewById<TextView>(R.id.material_cost).text = "₹ "+material.cost.toString()
        view.findViewById<TextView>(R.id.material_quantity).text = material.quantity+ " Ton(s)"
        view.findViewById<Button>(R.id.get_contact_details).setOnClickListener{
            if (auth.currentUser!!.isAnonymous){
                intent = Intent(this@SearchResultActivity,SignUpActivity::class.java)
                startActivity(intent)
            }
            else {
                try {
                    // Check if WhatsApp is installed
                    val intent = Intent(Intent.ACTION_VIEW)
                    val url = "https://api.whatsapp.com/send?phone=+91${material.number}"
                    intent.data = Uri.parse(url)
                    if (intent.resolveActivity(packageManager) != null) {
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Whatsapp not installed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(applicationContext, "Some error occurred", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
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
            intent = Intent(this@SearchResultActivity,ProductDetailedViewActivity::class.java)
            startActivity(intent)
        }

        searchResultBinding.searchResults.addView(view)
    }
    private fun dpToPx(sp: Float): Int {
        return applyDimension(
            COMPLEX_UNIT_DIP,
            sp,
            resources.displayMetrics
        ).toInt()
    }
}