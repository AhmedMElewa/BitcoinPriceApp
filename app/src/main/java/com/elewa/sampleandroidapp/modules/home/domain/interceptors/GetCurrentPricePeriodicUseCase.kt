package com.elewa.sampleandroidapp.modules.home.domain.interceptors

import com.elewa.sampleandroidapp.base.BaseUseCase
import com.elewa.sampleandroidapp.modules.home.domain.entites.CoinDeskEntity
import com.elewa.sampleandroidapp.modules.home.domain.repository.CoinDeskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetCurrentPricePeriodicUseCase @Inject constructor(private val getCurrentPriceUseCase: GetCurrentPriceUseCase) :
    BaseUseCase<Long, Flow<Result<CoinDeskEntity>>> {
    override suspend fun invoke(params: Long?):  Flow<Result<CoinDeskEntity>> {
        requireNotNull(params)
        return channelFlow {
            while (!isClosedForSend){
                delay(params)
                send(getCurrentPriceUseCase.invoke(Unit))
            }
        }.flowOn(Dispatchers.IO)
    }


}