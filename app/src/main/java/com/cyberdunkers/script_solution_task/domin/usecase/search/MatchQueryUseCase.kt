package com.cyberdunkers.script_solution_task.domin.usecase.search

import com.cyberdunkers.script_solution_task.domin.repository.SearchRepo
import javax.inject.Inject

class MatchQueryUseCase @Inject constructor(
    private val searchRepo: SearchRepo
) {
    operator fun invoke(query : String, firstName: String, lastName: String) : Boolean {
       return searchRepo.doesMatchSearchQuery(query , firstName , lastName)
    }
}