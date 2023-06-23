package com.elewa.sampleandroidapp.core.keys

object AppKeys {
    init {
        System.loadLibrary("native-lib")
    }

    external fun getBaseUrl(): String
}