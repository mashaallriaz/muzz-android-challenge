package com.muzz.androidchallenge.domain.interactors

import com.muzz.androidchallenge.domain.models.Message
import com.muzz.androidchallenge.domain.repository.MuzzRepository
import javax.inject.Inject

class SendMessage @Inject constructor(private val repository: MuzzRepository) :
    OneShotSuspendInteractor<Message, Unit>() {
    override suspend fun doWork(params: Message) {
        return repository.sendMessage(params)
    }
}