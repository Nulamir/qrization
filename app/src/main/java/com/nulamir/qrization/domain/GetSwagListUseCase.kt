package com.nulamir.qrization.domain

import androidx.lifecycle.LiveData

class GetSwagListUseCase (private val swagListRepository: SwagListRepository){
    fun getSwagList(): LiveData<List<SwagItem>> {
        return swagListRepository.getSwagList()
    }
}