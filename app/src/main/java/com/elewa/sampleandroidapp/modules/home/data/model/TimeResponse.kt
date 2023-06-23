package com.elewa.sampleandroidapp.modules.home.data.model

import com.google.gson.annotations.SerializedName

data class TimeResponse(
    @SerializedName("updated")
    val updated: String,
)