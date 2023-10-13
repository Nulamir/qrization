package com.nulamir.qrization.domain

interface SwagListRepository {

    fun addSwagItem(swagItem: SwagItem)
    fun deleteSwagItem(swagItem: SwagItem )
    fun editSwagItem (swagItem: SwagItem)
    fun getSwagItemsList(): List<SwagItem>
    fun getSwagItem(swagItemId: Int): SwagItem

}