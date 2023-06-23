package com.elewa.sampleandroidapp.modules.home.presentation.uimodel

data class CoinDeskState(
    val isLoading: Boolean = false,
    val data: CoinDeskUiModel? = null,
)