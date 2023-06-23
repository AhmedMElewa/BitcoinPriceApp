package com.elewa.sampleandroidapp.modules.home.data.mapper

import com.elewa.sampleandroidapp.modules.home.data.model.CoinDeskResponse
import com.elewa.sampleandroidapp.modules.home.domain.entites.CoinDeskEntity

fun CoinDeskResponse.toEntity() = CoinDeskEntity(
    updatedTime = time.updated,
    rate = bpi.USD.rate
)