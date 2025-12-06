package com.muzz.androidchallenge.domain.interactors

import com.muzz.androidchallenge.domain.models.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class OneShotSuspendInteractor<in P, T> {
    operator fun invoke(params: P): Flow<Result<T>> = flow {
        emit(Result.Loading())
        try {
            emit(Result.Success(doWork(params)))
        } catch (e: Exception) {
            emit(Result.Error(e.localizedMessage ?: "Unknown error"))
        }
    }

    protected abstract suspend fun doWork(params: P): T
}