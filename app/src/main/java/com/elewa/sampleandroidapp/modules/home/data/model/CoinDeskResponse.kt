package com.elewa.sampleandroidapp.modules.home.data.model

import com.google.gson.annotations.SerializedName

data class CoinDeskResponse(
    @SerializedName("time")
    val time: TimeResponse,
    @SerializedName("bpi")
    val bpi: BpiResponse,

    )








