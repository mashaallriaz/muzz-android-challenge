package com.muzz.androidchallenge.domain.interactors

import com.muzz.androidchallenge.data.datastore.MuzzPreferences
import com.muzz.androidchallenge.domain.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCurrentUser @Inject constructor(
    private val preferences: MuzzPreferences
) : ContinuousFlowInteractor<Unit, User>() {
    override fun doWork(params: Unit): Flow<User> {
        return preferences.activeUserId.map { id -> User.fromId(id) }
    }
}