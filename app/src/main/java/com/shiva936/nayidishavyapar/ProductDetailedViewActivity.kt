package com.shiva936.nayidishavyapar

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import com.shiva936.nayidishavyapar.databinding.ActivityDetailedItemResultBinding
import com.shiva936.nayidishavyapar.databinding.ActivityYourProductDetailedViewBinding
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.pdf.PdfDocument
import android.net.Uri
import android.os.Environment
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import android.util.Log
import android.util.TypedValue.COMPLEX_UNIT_DIP
import android.util.TypedValue.applyDimension
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

class ProductDetailedViewActivity : ComponentActivity() {
    private lateinit var productDetailedViewBinding: ActivityDetailedItemResultBinding
    private val database = FirebaseDatabase.getInstance()
    private val databaseRef = database.reference
    private val storage = FirebaseStorage.getInstance()
    private val auth = FirebaseAuth.getInstance()
    private var storageRef = storage.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productDetailedViewBinding = ActivityDetailedItemResultBinding.inflate(layoutInflater)
        val view = productDetailedViewBinding.root
        setContentView(view)
        load()

        productDetailedViewBinding.backButton.setOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }
        productDetailedViewBinding.download.setOnClickListener{

            productDetailedViewBinding.backButton.visibility = View.GONE
            productDetailedViewBinding.downloadFramework.visibility = View.GONE
            productDetailedViewBinding.minQuantity.visibility = View.GONE
            productDetailedViewBinding.minQuantityAns.visibility = View.GONE
            val bitmap = createBitmapFromView(view)
            createPdfFromBitmap(bitmap)
            productDetailedViewBinding.backButton.visibility = View.VISIBLE
            productDetailedViewBinding.downloadFramework.visibility = View.VISIBLE
            productDetailedViewBinding.minQuantity.visibility = View.VISIBLE
            productDetailedViewBinding.minQuantityAns.visibility = View.VISIBLE
        }
    }
    private fun load(){
        val path = intent.getStringExtra("path")
        val category = intent.getStringExtra("category")
        println(path)
        databaseRef.child(path!!).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val material = snapshot.getValue(MaterialDataClass::class.java)
                val image = material!!.images?.entries?.first()?.key
                val width = dpToPx(200f)
                val height = dpToPx(250f)
                storageRef.child("images/${image}.jpg").downloadUrl.addOnSuccessListener { uri ->
                    Glide.with(this@ProductDetailedViewActivity)
                        .load(uri).placeholder(R.drawable.agriculture_waste).override(width,height)
                        .into(productDetailedViewBinding.imageView)
                    println(uri)
                    productDetailedViewBinding.imageView.visibility = View.VISIBLE
                    println("done")
                }.addOnFailureListener {
                    // Handle errors
                    Toast.makeText(applicationContext, "Some error occurred", Toast.LENGTH_SHORT).show()
                }
                productDetailedViewBinding.txtPrice.text = "₹ "+material?.cost.toString()
                productDetailedViewBinding.productName.text = material?.name
                productDetailedViewBinding.productIndustry.text = category
                productDetailedViewBinding.location.text = material?.location
                productDetailedViewBinding.description.text = material?.description
                productDetailedViewBinding.minQuantityAns.text = material?.minimumQuantity + " " + material?.quantityMeasurement
                productDetailedViewBinding.natureAns.text = material?.specification
                productDetailedViewBinding.locationAns.text = material?.location
                productDetailedViewBinding.negotiableAns.text = material?.negotiable
                productDetailedViewBinding.transportationAns.text = material?.deliveryOptions
                productDetailedViewBinding.paymentMethodAns.text = material?.payment
                productDetailedViewBinding.minOrderAns.text =  material?.minimumQuantity + " " + material?.quantityMeasurement

                for (entry in material.images?.entries!!){
                    val imageView = ImageView(this@ProductDetailedViewActivity)
                    val layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    //layoutParams.setMargins(10, 10, 10, 10) // Optional margins
                    imageView.layoutParams = layoutParams
                    storageRef.child("images/${entry.key}.jpg").downloadUrl.addOnSuccessListener { uri ->
                        Glide.with(this@ProductDetailedViewActivity)
                            .load(uri).placeholder(R.drawable.agriculture_waste).override(300,300)
                            .into(imageView)
                        imageView.visibility = View.VISIBLE
                        productDetailedViewBinding.imageRootView.addView(imageView)

                    }.addOnFailureListener {
                        // Handle errors
                        Toast.makeText(applicationContext, "Some error occurred in displaying", Toast.LENGTH_SHORT).show()
                    }
                }
                productDetailedViewBinding.callOwner.setOnClickListener{
                    if (auth.currentUser!!.isAnonymous){
                        intent = Intent(this@ProductDetailedViewActivity,SignUpActivity::class.java)
                        startActivity(intent)
                    }
                    else {
                        try {
                            // Check if WhatsApp is installed
                            val intent = Intent(Intent.ACTION_VIEW)
                            val url = "https://api.whatsapp.com/send?phone=+919398815361${material.number}"
                            intent.data = Uri.parse(url)
                            if (intent.resolveActivity(packageManager) != null) {
                                startActivity(intent)
                            } else {
                                Toast.makeText(
                                    applicationContext,
                                    "Whatsapp not installed",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                            Toast.makeText(applicationContext, "Some error occurred", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
                productDetailedViewBinding.enquireNow.setOnClickListener{
                    if (auth.currentUser!!.isAnonymous){
                        intent = Intent(this@ProductDetailedViewActivity,SignUpActivity::class.java)
                        startActivity(intent)
                    }
                    else {
                        try {
                            // Check if WhatsApp is installed
                            val intent = Intent(Intent.ACTION_VIEW)
                            val url = "https://api.whatsapp.com/send?phone=+919398815361${material.number}"
                            intent.data = Uri.parse(url)
                            if (intent.resolveActivity(packageManager) != null) {
                                startActivity(intent)
                            } else {
                                Toast.makeText(
                                    applicationContext,
                                    "Whatsapp not installed",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                            Toast.makeText(applicationContext, "Some error occurred", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {
                //
                Toast.makeText(applicationContext, "Some error occurred", Toast.LENGTH_SHORT).show()
            }

        })
    }
    private fun createBitmapFromView(view: View): Bitmap {
        val width = view.width
        val height = view.height
        Log.d("Bitmap Size", "Width: $width, Height: $height")

        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }


    private fun createPdfFromBitmap(bitmap: Bitmap) {
        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(bitmap.width, bitmap.height, 1).create()
        val page = pdfDocument.startPage(pageInfo)
        val canvas = page.canvas
        canvas.drawBitmap(bitmap, 0f, 0f, null)
        pdfDocument.finishPage(page)

        val filePath = File(getExternalFilesDir(null), "product_detailed_view.pdf")
        Log.d("PDF Creation", "Saving PDF to: ${filePath.absolutePath}") // Log the file path

        try {
            filePath.parentFile?.mkdirs() // Ensure the directory exists
            val outputStream = FileOutputStream(filePath)
            pdfDocument.writeTo(outputStream)
            pdfDocument.close()
            outputStream.close()
            Log.d("PDF Creation", "PDF successfully created")
        } catch (e: IOException) {
            e.printStackTrace()
            Log.e("PDF Creation", "Error creating PDF: ${e.message}")
        }
    }
    private fun dpToPx(sp: Float): Int {
        return applyDimension(
            COMPLEX_UNIT_DIP,
            sp,
            resources.displayMetrics
        ).toInt()
    }

}