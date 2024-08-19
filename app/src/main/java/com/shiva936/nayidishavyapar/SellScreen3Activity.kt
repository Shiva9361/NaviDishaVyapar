package com.shiva936.nayidishavyapar

import android.content.Intent
import android.os.Bundle
import android.widget.CheckBox
import androidx.activity.ComponentActivity
import androidx.core.view.children
import com.shiva936.nayidishavyapar.databinding.ActivitySellScreen2Binding
import java.util.ArrayList

import android.Manifest
import android.app.DatePickerDialog
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.text.intl.Locale
import androidx.core.app.ActivityCompat
import com.shiva936.nayidishavyapar.databinding.ActivitySellScreen3Binding
import java.util.Calendar


class SellScreen3Activity : ComponentActivity() {

    private lateinit var sellScreen3Binding: ActivitySellScreen3Binding
    private var deliveryOption = "Pick Up"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sellScreen3Binding = ActivitySellScreen3Binding.inflate(layoutInflater)
        val view = sellScreen3Binding.root
        setContentView(view)
        /**
         * Initialization
         */
        sellScreen3Binding.fromDate.visibility = View.GONE
        sellScreen3Binding.toDate.visibility = View.GONE
        sellScreen3Binding.txtToDate.visibility = View.GONE
        sellScreen3Binding.txtFromDate.visibility = View.GONE
        sellScreen3Binding.availabilityImmediately.isChecked = true
        sellScreen3Binding.pickUp.setBackgroundResource(R.drawable.baby_pink_background_less_rounded)
        sellScreen3Binding.drop.setBackgroundResource(R.drawable.grey_rounded_border_background)

        /**
         * Listeners
         */
        sellScreen3Binding.pickUp.setOnClickListener {
            deliveryOption = "Pick Up"
            sellScreen3Binding.pickUp.setBackgroundResource(R.drawable.baby_pink_background_less_rounded)
            sellScreen3Binding.drop.setBackgroundResource(R.drawable.grey_rounded_border_background)
        }
        sellScreen3Binding.drop.setOnClickListener {
            deliveryOption = "Drop"
            sellScreen3Binding.drop.setBackgroundResource(R.drawable.baby_pink_background_less_rounded)
            sellScreen3Binding.pickUp.setBackgroundResource(R.drawable.grey_rounded_border_background)
        }
        sellScreen3Binding.availabilityImmediately.setOnClickListener {
            sellScreen3Binding.customDateRange.isChecked = false
            sellScreen3Binding.fromDate.visibility = View.GONE
            sellScreen3Binding.toDate.visibility = View.GONE
            sellScreen3Binding.txtToDate.visibility = View.GONE
            sellScreen3Binding.txtFromDate.visibility = View.GONE
        }
        sellScreen3Binding.customDateRange.setOnClickListener {
            sellScreen3Binding.availabilityImmediately.isChecked = false
            sellScreen3Binding.fromDate.visibility = View.VISIBLE
            sellScreen3Binding.toDate.visibility = View.VISIBLE
            sellScreen3Binding.txtToDate.visibility = View.VISIBLE
            sellScreen3Binding.txtFromDate.visibility = View.VISIBLE
        }
        sellScreen3Binding.fromDate.setOnClickListener {
            datePicker { day, month, year ->
                val selectedDate = "$day/${month + 1}/$year"
                sellScreen3Binding.fromDate.text = selectedDate
            }
        }
        sellScreen3Binding.toDate.setOnClickListener {
            datePicker { day, month, year ->
                val selectedDate = "$day/${month + 1}/$year"
                sellScreen3Binding.toDate.text = selectedDate
            }
        }
        sellScreen3Binding.next.setOnClickListener {
            if ((sellScreen3Binding.toDate.text.isNotEmpty() && sellScreen3Binding.fromDate.text.isNotEmpty() && sellScreen3Binding.fromDate.text.toString() < sellScreen3Binding.toDate.text.toString() ) || sellScreen3Binding.availabilityImmediately.isChecked){
                if(!sellScreen3Binding.locationData.selectedItem.equals("Select City")){
                    val childIntent = Intent(this@SellScreen3Activity,SellScreen6Activity::class.java)
                    if (intent.extras!=null){
                        childIntent.putExtras(intent.extras!!)
                    }
                    childIntent.putExtra("Delivery Option",deliveryOption)
                    if (sellScreen3Binding.availabilityImmediately.isChecked){
                        childIntent.putExtra("Available",true)
                    }
                    else {
                        childIntent.putExtra("From Data", sellScreen3Binding.toDate.text)
                        childIntent.putExtra("To Date", sellScreen3Binding.toDate.text)
                    }
                    startActivity(childIntent)
                }
                else{
                    Toast.makeText(applicationContext, "Select a City", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(applicationContext, "Enter Valid Dates!!", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun datePicker(onDateSelected: (day: Int, month: Int, year: Int) -> Unit) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                onDateSelected(selectedDay, selectedMonth, selectedYear)
            },
            year, month, day
        )
        datePickerDialog.show()
    }
//        sellScreen3Binding.fromDate.setOnClickListener{
//
//        }

//        sellScreen3Binding.locationSymbol.setOnClickListener {
//            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), 100)
//            } else {
//                enableLocation()
//            }
//        }


//    private fun enableLocation() {
//        val locationListener = object : LocationListener {
//            override fun onLocationChanged(location: Location) {
//                val latitude = location.latitude
//                val longitude = location.longitude
//                println(latitude)
//                println(longitude)
//                // Handle the location update (e.g., update UI)
//            }
//
//            override fun onProviderEnabled(provider: String) {}
//            override fun onProviderDisabled(provider: String) {}
//        }
//
//        val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
//        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//            startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
//        }
//        if (ActivityCompat.checkSelfPermission(
//                this, Manifest.permission.ACCESS_FINE_LOCATION
//            ) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
//                this, Manifest.permission.ACCESS_COARSE_LOCATION
//            ) == PackageManager.PERMISSION_GRANTED
//        ) {
//            locationManager.requestLocationUpdates(
//                LocationManager.GPS_PROVIDER,
//                0L,
//                0f,
//                locationListener
//            )
//        }
//        else{
//            Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//
//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (requestCode == 100) {
//            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                enableLocation()
//            } else {
//                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
}
