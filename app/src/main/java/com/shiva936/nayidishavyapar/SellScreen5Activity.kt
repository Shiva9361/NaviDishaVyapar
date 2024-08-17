package com.shiva936.nayidishavyapar

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.google.firebase.storage.FirebaseStorage
import com.shiva936.nayidishavyapar.databinding.ActivitySellScreen5Binding
import java.util.UUID

class SellScreen5Activity : ComponentActivity() {
    private val storage = FirebaseStorage.getInstance()
    private val storageRef = storage.reference

    private lateinit var sellScreen5Binding: ActivitySellScreen5Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sellScreen5Binding = ActivitySellScreen5Binding.inflate(layoutInflater)
        val view = sellScreen5Binding.root
        setContentView(view)
    }
    private fun uploadImageToFirebase(imageUri: Uri) {
        val imageRef = storageRef.child("images/${UUID.randomUUID()}.jpg")

        imageRef.putFile(imageUri)
            .addOnSuccessListener {
                // Image uploaded successfully
                Toast.makeText(this, "Upload successful!", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                // Handle any errors
                Toast.makeText(this, "Upload failed: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }
}