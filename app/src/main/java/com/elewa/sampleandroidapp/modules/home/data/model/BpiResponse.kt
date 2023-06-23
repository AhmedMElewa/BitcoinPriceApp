package com.elewa.sampleandroidapp.modules.home.data.model

import com.google.gson.annotations.SerializedName

data class BpiResponse(
    @SerializedName("USD")
    val USD: USDResponse,
)