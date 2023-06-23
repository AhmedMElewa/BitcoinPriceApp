package com.elewa.sampleandroidapp.modules.home.presentation.mapper

import com.elewa.sampleandroidapp.core.extention.formatTo
import com.elewa.sampleandroidapp.core.extention.toDate
import com.elewa.sampleandroidapp.modules.home.domain.entites.CoinDeskEntity
import com.elewa.sampleandroidapp.modules.home.presentation.uimodel.CoinDeskUiModel

fun CoinDeskEntity.toUiModel() = CoinDeskUiModel(
    date = updatedTime.toDate().formatTo(),
    rate = rate
)