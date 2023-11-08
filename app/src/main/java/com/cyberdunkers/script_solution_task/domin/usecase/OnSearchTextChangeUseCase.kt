package com.cyberdunkers.script_solution_task.domin.usecase

import com.cyberdunkers.script_solution_task.domin.repository.SearchRepo
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class OnSearchTextChangeUseCase @Inject constructor(
    private val searchRepo: SearchRepo
) {

    operator fun invoke(text: String , searchText : MutableStateFlow<String>)  {
        return searchRepo.onSearchTextChange(text, searchText)
    }
}