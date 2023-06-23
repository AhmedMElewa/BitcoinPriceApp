package com.elewa.sampleandroidapp.modules.home.data.source

import com.elewa.sampleandroidapp.modules.home.data.model.CoinDeskResponse
import retrofit2.Response
import retrofit2.http.GET

interface CoinDeskDataService {

    @GET("v1/bpi/currentprice.json")
    suspend fun getCurrentPrices(): Response<CoinDeskResponse>
}