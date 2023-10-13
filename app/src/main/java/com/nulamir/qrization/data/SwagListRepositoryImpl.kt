package com.nulamir.qrization.data

import com.nulamir.qrization.domain.SwagItem
import com.nulamir.qrization.domain.SwagListRepository

object SwagListRepositoryImpl : SwagListRepository {

    private val swagList = mutableListOf<SwagItem>()

    override fun addSwagItem(swagItem: SwagItem) {
        swagList
    }

    override fun deleteSwagItem(swagItem: SwagItem) {
        TODO("Not yet implemented")
    }

    override fun editSwagItem(swagItem: SwagItem) {
        TODO("Not yet implemented")
    }

    override fun getSwagList(): List<SwagItem> {
        TODO("Not yet implemented")
    }

    override fun getSwagItem(swagItemId: Int): SwagItem {
        TODO("Not yet implemented")
    }

}