package com.nulamir.qrization.domain

data class SwagItem (
    val id: Int,
    val name: String,
    val accountNumber: String,
    val accountType: String,
    val inventoryData: String,
    val unit: String,
    var realLocation : String,
    val accountLocation: String
)
