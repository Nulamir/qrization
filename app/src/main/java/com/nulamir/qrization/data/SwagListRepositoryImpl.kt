package com.nulamir.qrization.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nulamir.qrization.domain.SwagItem
import com.nulamir.qrization.domain.SwagListRepository
import java.lang.RuntimeException

object SwagListRepositoryImpl : SwagListRepository {

    private val swagListLD = MutableLiveData<List<SwagItem>>()
    private val swagList = mutableListOf<SwagItem>()


    private var autoIncrementId = 0

    init {
        for (i in 0 until 10) {
            val item = SwagItem("Name", i.toString(), "MC", "2023" , "IT" , "202" , "")
            addSwagItem(item)
        }
    }

    override fun addSwagItem(swagItem: SwagItem) {
        if (swagItem.id == SwagItem.UDEFINED_ID) {
            swagItem.id = autoIncrementId++
        }
        swagList.add(swagItem)
        updateSwagList()

    }

    override fun deleteSwagItem(swagItem: SwagItem) {
        swagList.remove(swagItem)
        updateSwagList()
    }

    override fun editSwagItem(swagItem: SwagItem) {

        val oldItem = getSwagItem(swagItem.id)
        deleteSwagItem(oldItem)
        addSwagItem(swagItem)

    }

    override fun getSwagList(): LiveData<List<SwagItem>> {
        return swagListLD
    }

    override fun getSwagItem(swagItemId: Int): SwagItem {
        return swagList.find {
            it.id == swagItemId
        } ?: throw RuntimeException("Element $swagItemId not found")
    }

    fun updateSwagList(){
        swagListLD.value = swagList.toList()
    }
}