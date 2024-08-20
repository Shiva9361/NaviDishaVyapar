package com.shiva936.nayidishavyapar

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.shiva936.nayidishavyapar.databinding.ActivitySellScreen1Binding

class SellScreen1Activity : ComponentActivity() {
    private lateinit var sellScreen1Binding: ActivitySellScreen1Binding
    private var materialType = "Agricultural"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sellScreen1Binding = ActivitySellScreen1Binding.inflate(layoutInflater)
        val view = sellScreen1Binding.root
        setContentView(view)

        sellScreen1Binding.ndy.setOnClickListener{
            val childIntent = Intent(this@SellScreen1Activity,MainMenuActivity::class.java)
            startActivity(childIntent)
        }
        sellScreen1Binding.backButton.setOnClickListener{
            val childIntent = Intent(this@SellScreen1Activity,MainMenuActivity::class.java)
            startActivity(childIntent)
        }
        sellScreen1Binding.frameOther.alpha = 0.5F
        sellScreen1Binding.frameAgriculture.alpha = 1F
        sellScreen1Binding.frameFood.alpha = 0.5F
        sellScreen1Binding.frameHealth.alpha = 0.5F
        sellScreen1Binding.frameMining.alpha = 0.5F
        sellScreen1Binding.frameOffice.alpha = 0.5F
        sellScreen1Binding.frameAuto.alpha = 0.5F
        sellScreen1Binding.frameManufacturing.alpha = 0.5F
        sellScreen1Binding.frameRetail.alpha = 0.5F
        sellScreen1Binding.frameHospitality.alpha = 0.5F

        val materials = listOf(sellScreen1Binding.frameAgriculture,sellScreen1Binding.frameAuto,sellScreen1Binding.frameFood,sellScreen1Binding.frameHealth,sellScreen1Binding.frameHospitality,sellScreen1Binding.frameMining,sellScreen1Binding.frameOffice,sellScreen1Binding.frameOther,sellScreen1Binding.frameManufacturing,sellScreen1Binding.frameRetail)
        for (material in materials){
            material.setOnClickListener{
                for (otherMaterial in materials){
                    otherMaterial.alpha = 0.5F
                }
                material.alpha = 1F
                materialType = when (material){
                    sellScreen1Binding.frameAgriculture -> "Agricultural"
                    sellScreen1Binding.frameOther -> "Other"
                    sellScreen1Binding.frameFood -> "Food"
                    sellScreen1Binding.frameHealth -> "Health"
                    sellScreen1Binding.frameMining -> "Mining"
                    sellScreen1Binding.frameOffice -> "Office"
                    sellScreen1Binding.frameAuto -> "Auto"
                    sellScreen1Binding.frameManufacturing -> "Manufacturing"
                    sellScreen1Binding.frameRetail -> "Retail"
                    else -> "Hospitality"
                }
            }
        }
        sellScreen1Binding.next.setOnClickListener {
            val name = sellScreen1Binding.editName.text
            val description = sellScreen1Binding.editDescription.text
            if (name.isNotEmpty() && description.isNotEmpty()){
                var childIntent = Intent(this@SellScreen1Activity,SellScreen2Activity::class.java)
                childIntent.putExtra("Category",materialType).putExtra("Name",name.toString()).putExtra("Description",description.toString())
                startActivity(childIntent)
            }
            else{
                Toast.makeText(applicationContext, "Fill all details", Toast.LENGTH_SHORT).show()
            }
        }
    }
}