package com.nulamir.qrization.domain

import androidx.lifecycle.LiveData

interface SwagListRepository {

    fun addSwagItem(swagItem: SwagItem)
    fun deleteSwagItem(swagItem: SwagItem )
    fun editSwagItem (swagItem: SwagItem)
    fun getSwagList(): LiveData<List<SwagItem>>
    fun getSwagItem(swagItemId: Int): SwagItem
    fun readSwagListFromExcel()

}