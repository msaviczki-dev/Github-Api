package com.example.githubusersapi.di

import com.example.githubusersapi.domain.usecase.GetGithubUserDetailUseCase
import com.example.githubusersapi.domain.usecase.GetGithubUserRepositoriesUseCase
import com.example.githubusersapi.domain.usecase.GetGithubUsersUseCase
import org.koin.dsl.module

fun provideDomainModule() = listOf(module {
    single { GetGithubUsersUseCase(repository = get()) }
    single { GetGithubUserDetailUseCase(repository = get()) }
    single { GetGithubUserRepositoriesUseCase(repository = get()) }
})