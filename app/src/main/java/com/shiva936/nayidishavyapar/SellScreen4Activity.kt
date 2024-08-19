package com.shiva936.nayidishavyapar

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.shiva936.nayidishavyapar.databinding.ActivitySellScreen4Binding

class SellScreen4Activity : ComponentActivity() {
    private lateinit var sellScreen4Binding: ActivitySellScreen4Binding
    private var negotiability = "Negotiable"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sellScreen4Binding = ActivitySellScreen4Binding.inflate(layoutInflater)
        val view = sellScreen4Binding.root
        setContentView(view)
        sellScreen4Binding.yesNegotiable.isChecked = true

        sellScreen4Binding.yesNegotiable.setOnClickListener {
            negotiability = "Negotiable"
            sellScreen4Binding.notNegotaible.isChecked = false
            sellScreen4Binding.yesNegotiable.isChecked = true
        }
        sellScreen4Binding.notNegotaible.setOnClickListener {
            negotiability = "Not Negotiable"
            sellScreen4Binding.notNegotaible.isChecked = true
            sellScreen4Binding.yesNegotiable.isChecked = false
        }
        sellScreen4Binding.next.setOnClickListener {
            if (sellScreen4Binding.txtPrice.text.isNotEmpty() && sellScreen4Binding.txtMinOrder.text.isNotEmpty()){
                val childIntent = Intent(this@SellScreen4Activity,SellScreen5Activity::class.java)
                if (intent.extras!=null){
                    childIntent.putExtras(intent.extras!!)
                }
                childIntent.putExtra("Negotiability",negotiability)
                childIntent.putExtra("Price",sellScreen4Binding.txtPrice.text.toString())
                childIntent.putExtra("Payment",sellScreen4Binding.paymentOptions.selectedItem.toString())
                childIntent.putExtra("Price Measurement",sellScreen4Binding.measurement.selectedItem.toString())
                childIntent.putExtra("Quantity Measurement",sellScreen4Binding.measurement.selectedItem.toString())
                childIntent.putExtra("Minimum Quantity",sellScreen4Binding.txtMinOrder.text.toString())
                startActivity(childIntent)
            }
            else{
                Toast.makeText(application, "Fill all details", Toast.LENGTH_SHORT).show()
            }
        }
    }
}