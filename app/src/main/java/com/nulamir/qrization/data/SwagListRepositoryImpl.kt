package com.nulamir.qrization.data

import com.nulamir.qrization.domain.SwagItem
import com.nulamir.qrization.domain.SwagListRepository
import java.lang.RuntimeException

object SwagListRepositoryImpl : SwagListRepository {

    private val swagList = mutableListOf<SwagItem>()

    private var autoIncrementId = 0

    override fun addSwagItem(swagItem: SwagItem) {
        if (swagItem.id == SwagItem.UDEFINED_ID) {
            swagItem.id = autoIncrementId++
        }
        swagList.add(swagItem)

    }

    override fun deleteSwagItem(swagItem: SwagItem) {
        swagList.remove(swagItem)
    }

    override fun editSwagItem(swagItem: SwagItem) {

        val oldItem = getSwagItem(swagItem.id)
        deleteSwagItem(oldItem)
        addSwagItem(swagItem)

    }

    override fun getSwagList(): List<SwagItem> {
        return swagList.toList()
    }

    override fun getSwagItem(swagItemId: Int): SwagItem {
        return swagList.find {
            it.id == swagItemId
        } ?: throw RuntimeException("Element $swagItemId not found")
    }

}