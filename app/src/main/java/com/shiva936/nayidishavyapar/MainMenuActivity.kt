package com.shiva936.nayidishavyapar


import android.content.Intent
import android.os.Bundle
import android.util.TypedValue.COMPLEX_UNIT_DIP
import android.util.TypedValue.COMPLEX_UNIT_SP
import android.util.TypedValue.applyDimension
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.HorizontalScrollView
import android.widget.ImageView
import com.shiva936.nayidishavyapar.SettingScreenActivity
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.animation.core.snap
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.shiva936.nayidishavyapar.databinding.ActivityMainMenuBinding

class MainMenuActivity : ComponentActivity() {
    private val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    private val databaseReference = database.reference
    private val storage = FirebaseStorage.getInstance()
    private var storageRef = storage.reference
    private val auth = FirebaseAuth.getInstance()

    private lateinit var mainMenuBinding: ActivityMainMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainMenuBinding = ActivityMainMenuBinding.inflate(layoutInflater)
        val view = mainMenuBinding.root
        setContentView(view)
        mainMenuBinding.Filter.setOnClickListener {
            intent = Intent(this@MainMenuActivity, SearchScreenActivity::class.java)
            startActivity(intent)
        }
        mainMenuBinding.searchMaterial.setOnClickListener {
            intent = Intent(this@MainMenuActivity, SearchScreenActivity::class.java)
            startActivity(intent)
        }

        mainMenuBinding.sell.setOnClickListener {
            if (auth.currentUser==null || auth.currentUser!!.isAnonymous){
                intent = Intent(this@MainMenuActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            else {
                intent = Intent(this@MainMenuActivity, SellScreen1Activity::class.java)
                startActivity(intent)
            }
        }
        mainMenuBinding.yourProducts.setOnClickListener {
            intent = Intent(this@MainMenuActivity,ProductsActivity::class.java)
            startActivity(intent)
        }

        mainMenuBinding.user.setOnClickListener {
            intent = Intent(this@MainMenuActivity, SettingScreenActivity::class.java)
            startActivity(intent)
        }
        if (auth.currentUser==null || auth.currentUser!!.isAnonymous){
            mainMenuBinding.login.setOnClickListener {
                intent = Intent(this@MainMenuActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        else{
            databaseReference.child("user_data").child(auth.currentUser!!.uid).child("name").addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                        mainMenuBinding.login.text = snapshot.getValue(String::class.java)
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle error

                }
            })
        }
        mainMenuBinding.login.setOnClickListener {
            intent = Intent(this@MainMenuActivity, LoginActivity::class.java)
            startActivity(intent)
        }
        mainMenuBinding.share.setOnClickListener {
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
        loadMaterials("Agricultural",mainMenuBinding.agriculturalWasteNearYou)
        loadMaterials("Manufacturing",mainMenuBinding.industrialWasteNearYou)
        loadMaterials("Food",mainMenuBinding.foodWasteNearYou)
        loadMaterials("Hospitality",mainMenuBinding.hospitalityWasteNearYou)
        loadMaterials("Office",mainMenuBinding.officeWasteeNearYou)
        loadMaterials("Automotive",mainMenuBinding.automotiveWasteNearYou)
        loadMaterials("Mining",mainMenuBinding.miningWasteNearYou)
        loadMaterials("Other",mainMenuBinding.otherWasteNearYou)
        loadMaterials("Health",mainMenuBinding.healthWasteNearYou)
        loadMaterials("Retail",mainMenuBinding.retailWasteNearYou)


    }
    private fun loadMaterials(materialType:String, horizontalScrollView: LinearLayout) {
        databaseReference.child("Categories").child(materialType).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                horizontalScrollView.removeAllViews()
                for (materialSnapshot in snapshot.children) {
                    val material = materialSnapshot.getValue(MaterialDataClass::class.java)
                    material?.let { addMaterialView(it,horizontalScrollView,"Categories/${materialType}/${materialSnapshot.key!!}",materialType) }
                }

            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
            })
    }
    private fun addMaterialView(material: MaterialDataClass,
                                horizontalScrollView: LinearLayout, path:String,category:String
    ) {
        val inflater = LayoutInflater.from(this)
        val materialView = inflater.inflate(R.layout.material_item, horizontalScrollView, false) as FrameLayout

        // Update materialView with material data
        materialView.findViewById<TextView>(R.id.txt_material_info_1).text = material.name
        val imageFrame = materialView.findViewById<ImageView>(R.id.item_image)
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
        materialView.setOnClickListener {
            val childIntent = Intent(this@MainMenuActivity,ProductDetailedViewActivity::class.java)
            childIntent.putExtra("path",path)
            childIntent.putExtra("category",category)
            startActivity(childIntent)
        }
        materialView.findViewById<TextView>(R.id.txt_cost_1).text = material.cost.toString()
        materialView.findViewById<TextView>(R.id.txt_location_a).text = material.location
        materialView.visibility = FrameLayout.VISIBLE
        // Add materialView to LinearLayout
        horizontalScrollView.addView(materialView)
    }

    private fun dpToPx(sp: Float): Int {
        return applyDimension(
            COMPLEX_UNIT_DIP,
            sp,
            resources.displayMetrics
        ).toInt()
    }

}