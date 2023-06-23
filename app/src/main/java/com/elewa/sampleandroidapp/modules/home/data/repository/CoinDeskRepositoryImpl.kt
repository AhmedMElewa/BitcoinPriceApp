package com.elewa.sampleandroidapp.modules.home.data.repository

import com.elewa.sampleandroidapp.core.expections.NetworkException
import com.elewa.sampleandroidapp.core.expections.ServerException
import com.elewa.sampleandroidapp.core.expections.UnknownException
import com.elewa.sampleandroidapp.modules.home.data.mapper.toEntity
import com.elewa.sampleandroidapp.modules.home.data.source.CoinDeskDataService
import com.elewa.sampleandroidapp.modules.home.domain.entites.CoinDeskEntity
import com.elewa.sampleandroidapp.modules.home.domain.repository.CoinDeskRepository
import javax.inject.Inject

class CoinDeskRepositoryImpl @Inject constructor(private val source: CoinDeskDataService) : CoinDeskRepository {

    override suspend fun getCurrentPrice(): Result<CoinDeskEntity> {
        return try {
            val response = source.getCurrentPrices()
            when(response.code()){
                200 ->{
                    if (response.body()!=null){
                        return Result.success(response.body()!!.toEntity())
                    }else{
                        return Result.failure(UnknownException)
                    }
                }
                401 ->{
                    return Result.failure(NetworkException)
                }
                501->{
                    return Result.failure(ServerException)
                }
                else ->{
                    return Result.failure(UnknownException)
                }
            }
        }catch (e: Exception){
            return Result.failure(e)
        }

    }
}