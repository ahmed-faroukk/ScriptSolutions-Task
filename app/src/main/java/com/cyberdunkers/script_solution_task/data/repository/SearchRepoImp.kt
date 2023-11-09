package com.cyberdunkers.script_solution_task.data.repository

import com.cyberdunkers.script_solution_task.domin.repository.SearchRepo
import kotlinx.coroutines.flow.MutableStateFlow

class SearchRepoImp : SearchRepo {
    override fun doesMatchSearchQuery(query: String, firstName: String, lastName: String): Boolean {
        val matchingCombinations = listOf(
            "$firstName$lastName",
            "$firstName $lastName",
            "${firstName.first()} ${lastName.first()}",
        )

        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }

    override fun onSearchTextChange(text: String, searchText: MutableStateFlow<String>) {
        searchText.value = text
    }

}