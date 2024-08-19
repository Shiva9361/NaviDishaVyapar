package com.shiva936.nayidishavyapar

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import com.shiva936.nayidishavyapar.databinding.ActivitySellScreen6Binding

class SellScreen6Activity : ComponentActivity() {
    private lateinit var sellScreen6Binding: ActivitySellScreen6Binding
    private var storage = FirebaseStorage.getInstance()
    private var storageRef = storage.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sellScreen6Binding = ActivitySellScreen6Binding.inflate(layoutInflater)
        val view = sellScreen6Binding.root
        setContentView(view)
        val category = intent.getStringExtra("Category")
        val name = intent.getStringExtra("Name")
        val description = intent.getStringExtra("Description")
        val quantity = intent.getStringExtra("Quantity")
        val condition = intent.getStringExtra("Condition")
        val specification = intent.getStringArrayListExtra("Specifications")
        val location = intent.getStringExtra("Location")
        val deliveryOption = intent.getStringExtra("Delivery Option")
        val availability = intent.getBooleanExtra("Available",false)
        val fromDate = intent.getStringExtra("From Date")
        val toDate = intent.getStringExtra("To Date")
        val negotiability = intent.getStringExtra("Negotiability")
        val payment = intent.getStringExtra("Payment")
        val price = intent.getStringExtra("Price")
        val priceMeasurement = intent.getStringExtra("Price Measurement")
        val minimumQuantity = intent.getStringExtra("Minimum Quantity")
        val quantityMeasurement = intent.getStringExtra("Quantity Measurement")
        val images = intent.getStringArrayListExtra("Images")

        sellScreen6Binding.backArrow.setOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }
        sellScreen6Binding.editSearch.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        sellScreen6Binding.materialNameAns.text = name
        sellScreen6Binding.materialTypeAns.text = category
        sellScreen6Binding.productDescriptionAns.text = description
        sellScreen6Binding.quantityAns.text = quantity
        sellScreen6Binding.materialConditionAns.text = condition
        sellScreen6Binding.natureAns.text = specification?.joinToString(", ")?:""
        sellScreen6Binding.locationAns.text = location
        sellScreen6Binding.transportationAns.text = deliveryOption
        if(availability){
            sellScreen6Binding.availabilityDateAns.text = "Instant"
        }
        else{
            sellScreen6Binding.availabilityDateAns.text = fromDate+"-"+toDate
        }
        sellScreen6Binding.priceAns.text = price+" "+priceMeasurement
        sellScreen6Binding.negotiableAns.text = negotiability
        sellScreen6Binding.paymentMethodAns.text = payment
        sellScreen6Binding.minOrderAns.text = minimumQuantity+" "+quantityMeasurement

        if (images != null) {
            for (image in images){
                val imageView = ImageView(this@SellScreen6Activity)
                val layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                //layoutParams.setMargins(10, 10, 10, 10) // Optional margins
                imageView.layoutParams = layoutParams
                storageRef.child(image).downloadUrl.addOnSuccessListener { uri ->
                    Glide.with(this)
                        .load(uri).placeholder(R.drawable.agriculture_waste).override(300,300)
                        .into(imageView)
                    println(uri)
                    imageView.visibility = View.VISIBLE
                    sellScreen6Binding.imageRootView.addView(imageView)
                    println("done")
                }.addOnFailureListener {
                    // Handle errors
                    Toast.makeText(applicationContext, "Some error occurred", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}