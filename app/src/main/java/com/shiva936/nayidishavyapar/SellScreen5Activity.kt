package com.shiva936.nayidishavyapar

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import com.shiva936.nayidishavyapar.databinding.ActivitySellScreen5Binding
import java.util.UUID

class SellScreen5Activity : ComponentActivity() {
    private val storage = FirebaseStorage.getInstance()
    private val storageRef = storage.reference
    private val images = mutableSetOf<String>()
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

        sellScreen5Binding.ndy.setOnClickListener{
            val childIntent = Intent(this@SellScreen5Activity,MainMenuActivity::class.java)
            startActivity(childIntent)
        }
        sellScreen5Binding.backArrow.setOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun uploadImageToFirebase(imageUri: Uri) {
        val imageUUID = UUID.randomUUID()
        val imageName = "images/${imageUUID}.jpg"
        val imageRef = storageRef.child(imageName)


        imageRef.putFile(imageUri)
            .addOnSuccessListener {
                // Image uploaded successfully
                Toast.makeText(this, "Upload successful!", Toast.LENGTH_SHORT).show()
                val imageView = ImageView(this@SellScreen5Activity)
                val layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                //layoutParams.setMargins(10, 10, 10, 10) // Optional margins
                imageView.layoutParams = layoutParams
                storageRef.child(imageName).downloadUrl.addOnSuccessListener { uri ->
                    Glide.with(this)
                        .load(uri).placeholder(R.drawable.agriculture_waste).override(300,300)
                        .into(imageView)
                    imageView.visibility = View.VISIBLE
                    imageView.setOnClickListener {
                        imageRef.delete().addOnSuccessListener {
                            sellScreen5Binding.imageRootView.removeView(imageView)
                            images.remove(imageName)

                        }.addOnFailureListener { exception ->
                            Toast.makeText(applicationContext, "Error : ${exception.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                    sellScreen5Binding.imageRootView.addView(imageView)

                }.addOnFailureListener {
                    // Handle errors
                    Toast.makeText(applicationContext, "Some error occurred in displaying", Toast.LENGTH_SHORT).show()
                }
                images.add(imageName)
            }
            .addOnFailureListener {
                // Handle any errors
                Toast.makeText(this, "Upload failed: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }
}