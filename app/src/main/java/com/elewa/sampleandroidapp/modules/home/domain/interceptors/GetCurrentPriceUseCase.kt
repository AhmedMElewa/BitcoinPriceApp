package com.elewa.sampleandroidapp.modules.home.domain.interceptors

import com.elewa.sampleandroidapp.base.BaseUseCase
import com.elewa.sampleandroidapp.modules.home.domain.entites.CoinDeskEntity
import com.elewa.sampleandroidapp.modules.home.domain.repository.CoinDeskRepository
import javax.inject.Inject

class GetCurrentPriceUseCase @Inject constructor(private val repository: CoinDeskRepository) :
    BaseUseCase<Unit, Result<CoinDeskEntity>> {
    override suspend fun invoke(params: Unit?): Result<CoinDeskEntity> {
        return repository.getCurrentPrice()

    }


}