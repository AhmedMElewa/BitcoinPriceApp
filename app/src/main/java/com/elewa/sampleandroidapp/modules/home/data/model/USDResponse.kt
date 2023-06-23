package com.elewa.sampleandroidapp.modules.home.data.model

import com.google.gson.annotations.SerializedName

data class USDResponse(
    @SerializedName("rate")
    val rate: String,
)