package com.cyberdunkers.script_solution_task.di

import com.cyberdunkers.script_solution_task.data.repository.SearchRepoImp
import com.cyberdunkers.script_solution_task.domin.repository.SearchRepo
import com.cyberdunkers.script_solution_task.domin.usecase.search.HandleSearchUseCases
import com.cyberdunkers.script_solution_task.domin.usecase.search.MatchQueryUseCase
import com.cyberdunkers.script_solution_task.domin.usecase.search.OnSearchTextChangeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRepo(): SearchRepo =
        SearchRepoImp()


    @Provides
    @Singleton
    fun provideHandleSearchUseCases(
        searchRepo: SearchRepo,
    ) = HandleSearchUseCases(
        MatchQueryUseCase(searchRepo),
        OnSearchTextChangeUseCase(searchRepo)
    )


}