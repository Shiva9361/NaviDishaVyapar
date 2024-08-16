package com.shiva936.nayidishavyapar


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.HorizontalScrollView
import com.shiva936.nayidishavyapar.SettingScreenActivity
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.shiva936.nayidishavyapar.databinding.ActivityMainMenuBinding

class MainMenuActivity : ComponentActivity() {
    private val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    private val databaseReference = database.reference
    private val auth : FirebaseAuth = FirebaseAuth.getInstance()

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
        mainMenuBinding.user.setOnClickListener {
            intent = Intent(this@MainMenuActivity, SettingScreenActivity::class.java)
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
        loadMaterials("Industrial",mainMenuBinding.industrialWasteNearYou)
    }
    private fun loadMaterials(materialType:String, horizontalScrollView: LinearLayout) {
        databaseReference.child("Categories").child(materialType).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (materialSnapshot in snapshot.children) {
                    val material = materialSnapshot.getValue(MaterialDataClass::class.java)
                    material?.let { addMaterialView(it,horizontalScrollView) }

                    println(material)
                }

            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
            })
    }
    private fun addMaterialView(material: MaterialDataClass,
                                horizontalScrollView: LinearLayout
    ) {
        val inflater = LayoutInflater.from(this)
        val materialView = inflater.inflate(R.layout.material_item, horizontalScrollView, false) as FrameLayout

        // Update materialView with material data
        materialView.findViewById<TextView>(R.id.txt_material_info_1).text = material.name

        materialView.findViewById<TextView>(R.id.txt_cost_1).text = material.cost.toString()
        materialView.findViewById<TextView>(R.id.txt_location_a).text = material.location
        materialView.visibility = FrameLayout.VISIBLE
        // Add materialView to LinearLayout
        horizontalScrollView.addView(materialView)
    }

}