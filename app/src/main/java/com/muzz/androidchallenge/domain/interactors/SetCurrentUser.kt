package com.muzz.androidchallenge.domain.interactors

import com.muzz.androidchallenge.data.datastore.MuzzPreferences
import javax.inject.Inject

class SetCurrentUser @Inject constructor(
    private val preferences: MuzzPreferences
) : OneShotSuspendInteractor<Int, Unit>() {
    override suspend fun doWork(params: Int) {
        preferences.setActiveUserId(params)
    }
}