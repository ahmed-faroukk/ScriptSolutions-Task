package com.cyberdunkers.script_solution_task.domin.repository

import kotlinx.coroutines.flow.MutableStateFlow

interface SearchRepo {
    fun doesMatchSearchQuery(query : String, firstName: String, lastName: String): Boolean
    fun onSearchTextChange(text: String , searchText : MutableStateFlow<String>)
}