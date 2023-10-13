package com.nulamir.qrization.domain

class GetSwagItemsListUseCase (private val swagListRepository: SwagListRepository){
    fun getSwagItemsList(): List<SwagItem> {
        return swagListRepository.getSwagItemsList()
    }
}