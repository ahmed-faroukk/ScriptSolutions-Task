package com.cyberdunkers.script_solution_task.domin.repository

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserverRepo {

    fun observe(): Flow<Status>

    enum class Status {
        Available, Unavailable, Losing, Lost
    }
}