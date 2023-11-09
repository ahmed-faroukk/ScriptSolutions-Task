package com.cyberdunkers.script_solution_task.domin.usecase.search

data class HandleSearchUseCases(
    val matchQueryUseCase: MatchQueryUseCase,
    val onSearchTextChangeUseCase: OnSearchTextChangeUseCase,
)
