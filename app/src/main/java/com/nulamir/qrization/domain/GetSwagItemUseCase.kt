package com.nulamir.qrization.domain

class GetSwagItemUseCase(private val swagListRepository: SwagListRepository) {
    fun getSwagItem(swagItemId: Int): SwagItem {
        return swagListRepository.getSwagItem(swagItemId)
    }
}