package com.muzz.androidchallenge.domain.interactors

import com.muzz.androidchallenge.domain.models.Message
import com.muzz.androidchallenge.domain.repository.MuzzRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllMessages @Inject constructor(
    private val repository: MuzzRepository
) : ContinuousFlowInteractor<Unit, List<Message>>() {
    override fun doWork(params: Unit): Flow<List<Message>> {
        return repository.getAllMessages()
    }
}