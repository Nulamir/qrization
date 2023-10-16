package com.nulamir.qrization.presentation

import androidx.lifecycle.ViewModel
import com.nulamir.qrization.data.SwagListRepositoryImpl
import com.nulamir.qrization.domain.DeleteSwagItemUseCase
import com.nulamir.qrization.domain.EditSwagItemUseCase
import com.nulamir.qrization.domain.GetSwagListUseCase

class MainViewModel : ViewModel() {

    private val repository = SwagListRepositoryImpl

    private val getSwagListUseCase = GetSwagListUseCase(repository)
    private val deleteSwagItemUseCase = DeleteSwagItemUseCase(repository)
    private val editSwagItemUseCase = EditSwagItemUseCase(repository)

}