package com.muzz.androidchallenge.domain.interactors

import com.muzz.androidchallenge.domain.models.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

abstract class ContinuousFlowInteractor<in P, T> {
    operator fun invoke(params: P): Flow<Result<T>> = doWork(params)
        .map<T, Result<T>> { Result.Success(it) }
        .onStart { emit(Result.Loading()) }
        .catch { exception ->
            emit(Result.Error(exception.localizedMessage ?: "Unknown error"))
        }

    protected abstract fun doWork(params: P): Flow<T>
}