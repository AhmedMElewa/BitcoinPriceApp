package com.elewa.sampleandroidapp.base

/**
 * Base Use Case class
 */
interface BaseUseCase<Params, Result> {
    suspend operator fun invoke(params: Params?): Result
}