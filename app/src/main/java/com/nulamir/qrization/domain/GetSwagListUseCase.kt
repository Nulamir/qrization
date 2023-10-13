package com.nulamir.qrization.domain

class GetSwagListUseCase (private val swagListRepository: SwagListRepository){
    fun getSwagList(): List<SwagItem> {
        return swagListRepository.getSwagList()
    }
}