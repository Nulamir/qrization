package com.nulamir.qrization.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nulamir.qrization.data.SwagListRepositoryImpl
import com.nulamir.qrization.domain.DeleteSwagItemUseCase
import com.nulamir.qrization.domain.EditSwagItemUseCase
import com.nulamir.qrization.domain.GetSwagListUseCase
import com.nulamir.qrization.domain.ReadSwagListFromExcelUseCase
import com.nulamir.qrization.domain.SwagItem

class MainViewModel : ViewModel() {

    private val repository = SwagListRepositoryImpl

    private val getSwagListUseCase = GetSwagListUseCase(repository)
    private val deleteSwagItemUseCase = DeleteSwagItemUseCase(repository)
    private val editSwagItemUseCase = EditSwagItemUseCase(repository)
    private val readSwagListFromExcelUseCase = ReadSwagListFromExcelUseCase(repository)

    val swagList = getSwagListUseCase.getSwagList()

    fun readSwagListFromExcel(){
        readSwagListFromExcelUseCase.readSwagListFromExcel()
    }

    fun deleteSwagItem(swagItem: SwagItem){
        deleteSwagItemUseCase.deleteSwagItem(swagItem)

    }

    fun changeRealLocation(swagItem: SwagItem, realLocation : String = "201"){

        val newItem = swagItem.copy(realLocation = realLocation)
        editSwagItemUseCase.editSwagItem(newItem)

    }
}