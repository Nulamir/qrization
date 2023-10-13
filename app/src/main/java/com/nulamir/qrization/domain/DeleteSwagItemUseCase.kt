package com.nulamir.qrization.domain

class DeleteSwagItemUseCase (private val swagListRepository: SwagListRepository) {
    fun deleteSwagItem(swagItem: SwagItem ){
        swagListRepository.deleteSwagItem(swagItem)
    }
}