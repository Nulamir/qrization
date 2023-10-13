package com.nulamir.qrization.domain

class EditSwagItemUseCase (private val swagListRepository: SwagListRepository) {
    fun editSwagItem (swagItem: SwagItem) {
        swagListRepository.editSwagItem(swagItem)
    }
}