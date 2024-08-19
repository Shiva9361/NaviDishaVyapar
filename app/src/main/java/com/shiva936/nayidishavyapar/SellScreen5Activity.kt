package com.shiva936.nayidishavyapar

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.storage.FirebaseStorage
import com.shiva936.nayidishavyapar.databinding.ActivitySellScreen5Binding
import java.util.UUID

class SellScreen5Activity : ComponentActivity() {
    private val storage = FirebaseStorage.getInstance()
    private val storageRef = storage.reference
    private val images = arrayListOf<String>()
    private val pickImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            uploadImageToFirebase(it)
        }
    }
    private lateinit var sellScreen5Binding: ActivitySellScreen5Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sellScreen5Binding = ActivitySellScreen5Binding.inflate(layoutInflater)
        val view = sellScreen5Binding.root
        setContentView(view)
        sellScreen5Binding.buttonUploadImage.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }
        sellScreen5Binding.next.setOnClickListener {
            val childIntent = Intent(this@SellScreen5Activity,SellScreen6Activity::class.java)
            if (intent.extras!=null){
                childIntent.putExtras(intent.extras!!)
            }
            if (images.isNotEmpty()){
                childIntent.putStringArrayListExtra("Images",ArrayList(images))
                startActivity(childIntent)
            }
            else{
                Toast.makeText(applicationContext, "Upload at least one Image", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun uploadImageToFirebase(imageUri: Uri) {
        val imageName = UUID.randomUUID()
        val imageRef = storageRef.child("images/${imageName}.jpg")


        imageRef.putFile(imageUri)
            .addOnSuccessListener {
                // Image uploaded successfully
                Toast.makeText(this, "Upload successful!", Toast.LENGTH_SHORT).show()
                images.add(imageName.toString())
            }
            .addOnFailureListener {
                // Handle any errors
                Toast.makeText(this, "Upload failed: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }
}