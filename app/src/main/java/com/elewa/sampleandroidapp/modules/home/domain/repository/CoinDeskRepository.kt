package com.elewa.sampleandroidapp.modules.home.domain.repository

import com.elewa.sampleandroidapp.modules.home.domain.entites.CoinDeskEntity

interface CoinDeskRepository {

    suspend fun getCurrentPrice(): Result<CoinDeskEntity>
}