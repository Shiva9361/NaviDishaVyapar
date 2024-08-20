package com.shiva936.nayidishavyapar

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import com.shiva936.nayidishavyapar.databinding.ActivityDetailedItemResultBinding
import com.shiva936.nayidishavyapar.databinding.ActivityYourProductDetailedViewBinding
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.pdf.PdfDocument
import android.os.Environment
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import android.util.Log

class ProductDetailedViewActivity : ComponentActivity() {
    private lateinit var productDetailedViewBinding: ActivityDetailedItemResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productDetailedViewBinding = ActivityDetailedItemResultBinding.inflate(layoutInflater)
        val view = productDetailedViewBinding.root
        setContentView(view)

        productDetailedViewBinding.backButton.setOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }
        productDetailedViewBinding.download.setOnClickListener{

            productDetailedViewBinding.backButton.visibility = View.GONE
            productDetailedViewBinding.downloadFramework.visibility = View.GONE
            productDetailedViewBinding.getPhoneNumber.visibility = View.GONE
            productDetailedViewBinding.connectNow.visibility = View.GONE
            productDetailedViewBinding.minQuantity.visibility = View.GONE
            productDetailedViewBinding.minQuantityAns.visibility = View.GONE
            val bitmap = createBitmapFromView(view)
            createPdfFromBitmap(bitmap)
            productDetailedViewBinding.backButton.visibility = View.VISIBLE
            productDetailedViewBinding.downloadFramework.visibility = View.VISIBLE
            productDetailedViewBinding.getPhoneNumber.visibility = View.VISIBLE
            productDetailedViewBinding.connectNow.visibility = View.VISIBLE
            productDetailedViewBinding.minQuantity.visibility = View.VISIBLE
            productDetailedViewBinding.minQuantityAns.visibility = View.VISIBLE
        }
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

}