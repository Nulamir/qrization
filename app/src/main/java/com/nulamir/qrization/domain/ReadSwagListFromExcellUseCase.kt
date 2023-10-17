package com.nulamir.qrization.domain
class ReadSwagListFromExcelUseCase (private val swagListRepository: SwagListRepository) {
    fun readSwagListFromExcel () {
        swagListRepository.readSwagListFromExcel()
    }
}
