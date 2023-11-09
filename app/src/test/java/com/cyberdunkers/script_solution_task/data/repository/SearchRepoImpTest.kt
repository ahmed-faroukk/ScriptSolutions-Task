package com.cyberdunkers.script_solution_task.data.repository

import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Assert.*

import org.junit.Test

class SearchRepoImpTest {

    // Good scenarios

    @Test
    fun testDoesMatchSearchQueryGoodScenarios() {
        val repo = SearchRepoImp() // Instantiate your class (replace YourClass with the actual class name)

        // Exact Match
        assertTrue(repo.doesMatchSearchQuery("John", "John", "Doe"))

        // Partial Match
        assertTrue(repo.doesMatchSearchQuery("Jo", "John", "Doe"))

        // Case Insensitivity
        assertTrue(repo.doesMatchSearchQuery("jOhN", "John", "Doe"))

    }

    // Bad scenarios

    @Test
    fun testDoesMatchSearchQueryBadScenarios() {
        val repo = SearchRepoImp()

        // No Match
        assertFalse(repo.doesMatchSearchQuery("Alice", "John", "Doe"))

    }

    @Test
    fun testOnSearchTextChangeGoodScenario() {
        val repo = SearchRepoImp()
        val initialText = "Initial Text"
        val searchText = MutableStateFlow(initialText)
        val newText = "New Text"

        repo.onSearchTextChange(newText, searchText)

        assertEquals(newText, searchText.value)
    }

    @Test
    fun testOnSearchTextChangeBadScenario() {
        val repo = SearchRepoImp()
        val initialText = "Initial Text"
        val searchText = MutableStateFlow(initialText)
        val newText = "Initial Text"

        repo.onSearchTextChange(newText, searchText)

        // Ensure that the searchText remains the same
        assertEquals(initialText, searchText.value)
    }
}