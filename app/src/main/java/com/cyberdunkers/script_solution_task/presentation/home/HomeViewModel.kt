package com.cyberdunkers.script_solution_task.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cyberdunkers.script_solution_task.domin.usecase.search.HandleSearchUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val handleSearchUseCases: HandleSearchUseCases,

) : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _persons = MutableStateFlow(clients)
    val persons = searchText



        .debounce(1000L)
        .onEach { _isSearching.update { true } }
        .combine(_persons) { text, persons ->
            if (text.isBlank()) {
                persons
            } else {
                delay(2000L)
                persons.filter {
                    handleSearchUseCases.matchQueryUseCase(text, it.firstName, it.lastName)
                }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _persons.value
        )


    fun onSearchTextChange(text: String) {
        handleSearchUseCases.onSearchTextChangeUseCase.invoke(
            text,
            _searchText
        )

    }
}

data class Clint(
    val firstName: String,
    val lastName: String,
)

private val clients = listOf(
    Clint("ahmed", "Farouk"),
    Clint("sayed", "samy"),
    Clint("hany", "mohamed"),
    Clint("fady", "bakery"),
    Clint("saly", "osama"),
    Clint("yousef", "mourad"),
    Clint("mourad", "emad"),
    Clint("mo", "salah"),
    Clint("fake", "clint"),
    Clint("gorge", "wassoof"),
    Clint("hany", "ramzy"),
    Clint("saly", "osama"),
    Clint("yousef", "mourad"),
    Clint("mourad", "emad"),
    Clint("mo", "salah"),
    Clint("mourad", "emad"),
    Clint("mo", "salah"),
    Clint("fake", "clint"),
    Clint("gorge", "wassoof"),
    Clint("fake", "clint")
)


