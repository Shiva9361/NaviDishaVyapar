package com.shiva936.nayidishavyapar
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SellScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sell_screen)

        // Initialize Spinners
        val productTypeSpinner: Spinner = findViewById(R.id.product_type)
        val quantityUnitSpinner: Spinner = findViewById(R.id.quantity_unit)
        val minOrderUnitSpinner: Spinner = findViewById(R.id.min_order_unit)

        // Get the string arrays from resources
        val productTypes = resources.getStringArray(R.array.product_types)
        val units = resources.getStringArray(R.array.units) // Ensure you have this string array defined

        // Set up adapters for Spinners
        val productTypeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, productTypes)
        productTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        productTypeSpinner.adapter = productTypeAdapter

        val unitAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, units)
        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        quantityUnitSpinner.adapter = unitAdapter
        minOrderUnitSpinner.adapter = unitAdapter

        // Initialize other views
        val productNameEditText: EditText = findViewById(R.id.product_name)
        val productQuantityEditText: EditText = findViewById(R.id.product_quantity)
        val minOrderQuantityEditText: EditText = findViewById(R.id.min_order_quantity)
        val costPerUnitEditText: EditText = findViewById(R.id.cost_per_unit)
        val locationEditText: EditText = findViewById(R.id.location)
        val productDescriptionEditText: EditText = findViewById(R.id.product_description)
        val uploadPhotoButton: Button = findViewById(R.id.upload_photo)
        val submitButton: Button = findViewById(R.id.submit_button)

        // Set up the submit button click listener
        submitButton.setOnClickListener {
            val productName = productNameEditText.text.toString().trim()
            val productType = productTypeSpinner.selectedItem.toString()
            val quantity = productQuantityEditText.text.toString().trim()
            val quantityUnit = quantityUnitSpinner.selectedItem.toString()
            val minOrderQuantity = minOrderQuantityEditText.text.toString().trim()
            val minOrderUnit = minOrderUnitSpinner.selectedItem.toString()
            val costPerUnit = costPerUnitEditText.text.toString().trim()
            val location = locationEditText.text.toString().trim()
            val description = productDescriptionEditText.text.toString().trim()

            // Perform form validation or data submission here
            if (productName.isEmpty() || quantity.isEmpty() || minOrderQuantity.isEmpty() ||
                costPerUnit.isEmpty() || location.isEmpty() || description.isEmpty()) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Proceed with form submission logic
            Toast.makeText(this, "Product information submitted successfully!", Toast.LENGTH_SHORT).show()
        }

        // Set up the upload photo button click listener
        uploadPhotoButton.setOnClickListener {
            // Handle photo upload logic here
            Toast.makeText(this, "Upload photo button clicked", Toast.LENGTH_SHORT).show()
        }
    }
}
