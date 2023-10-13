package com.nulamir.qrization.domain

class AddSwagItemUseCase(private val swagListRepository: SwagListRepository) {
    fun addSwagItem(swagItem: SwagItem) {
        swagListRepository.addSwagItem(swagItem)
    }
}