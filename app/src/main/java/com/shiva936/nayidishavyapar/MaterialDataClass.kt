package com.shiva936.nayidishavyapar

import com.google.android.gms.common.GoogleApiAvailability

data class MaterialDataClass(
    val name: String = "",
    val cost: Int = 0,
    val costUnit: String ="",
    val location: String = "",
    val quantity: String = "",
    val number: String = "",
    val description: String = "",
    val condition:String="",
    val specification:String="",
    val deliveryOptions: String="",
    val availability: String="",
    val minimumQuantity: String="",
    val quantityMeasurement: String="",
    val negotiable: String="",
    val payment: String="",
    val images: Map<String, Boolean>? = null,
)