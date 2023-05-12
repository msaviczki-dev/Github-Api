package com.example.githubusersapi.di

import com.example.githubusersapi.presentation.github.viewmodel.GithubViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun providePresentationModule() = listOf(module {
    viewModel {
        GithubViewModel(
            getUserUseCase = get(), getUserDetailUseCase = get(), getUserRepositoriesUseCase = get()
        )
    }
})