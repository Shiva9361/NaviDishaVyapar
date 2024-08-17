package com.shiva936.nayidishavyapar

import android.content.Intent
import android.os.Bundle
import android.widget.CheckBox
import androidx.activity.ComponentActivity
import androidx.core.view.children
import com.shiva936.nayidishavyapar.databinding.ActivitySellScreen2Binding
import java.util.ArrayList

class SellScreen2Activity : ComponentActivity() {
    private lateinit var sellScreen2Binding: ActivitySellScreen2Binding
    private var selectedQuantity = "1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sellScreen2Binding = ActivitySellScreen2Binding.inflate(layoutInflater)
        val view = sellScreen2Binding.root
        setContentView(view)
        val quantities = listOf(sellScreen2Binding.oneTon,sellScreen2Binding.twoTon,sellScreen2Binding.threeTon,sellScreen2Binding.fourTon,sellScreen2Binding.moreTon)

        sellScreen2Binding.oneTon.setBackgroundResource(R.drawable.baby_pink_background_rounded)

        for (quantity in quantities){
            quantity.setOnClickListener{
                for (otherQuantity in quantities ){
                    otherQuantity.setBackgroundResource(R.drawable.grey_rounded_border_background)
                }
                quantity.setBackgroundResource(R.drawable.baby_pink_background_rounded)
                selectedQuantity = when(quantity){
                    sellScreen2Binding.oneTon -> "1"
                    sellScreen2Binding.twoTon -> "2"
                    sellScreen2Binding.threeTon -> "3"
                    sellScreen2Binding.fourTon -> "4"
                    else -> ">4"
                }
            }
        }
        sellScreen2Binding.next.setOnClickListener {
            val specifications = mutableListOf<String>()
            for (index in 0 until sellScreen2Binding.specifications.childCount){
                val child = sellScreen2Binding.specifications.getChildAt(index)
                if (child is CheckBox && child.isChecked){
                    specifications.add(child.text.toString())
                }
            }
            val condition = sellScreen2Binding.dropDownMaterialCondition.selectedItem.toString()
            val childIntent = Intent(this@SellScreen2Activity,SellScreen3Activity::class.java)
            if (intent.extras!=null){
                childIntent.putExtras(intent.extras!!)
            }
            childIntent.putExtra("Quantity",selectedQuantity)
            childIntent.putExtra("Condition",condition)
            childIntent.putStringArrayListExtra("Specifications", ArrayList(specifications))
            startActivity(childIntent)
        }

    }
}