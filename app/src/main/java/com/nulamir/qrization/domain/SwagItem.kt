package com.nulamir.qrization.domain

data class SwagItem (

    val name: String,
    val accountNumber: String,
 //   val accountType: String,
 //   val inventoryData: String,
    val accountLocation: String,
    var realLocation : String,
    val comment: String,
    val unit: String,
    val enabled: String,
    var id: Int = UDEFINED_ID,

)

{
    companion object {
        const val UDEFINED_ID = -1
    }

}
