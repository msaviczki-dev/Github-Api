package com.example.githubusersapi.di

import com.example.githubusersapi.domain.usecase.GetGithubUsersUseCase
import org.koin.dsl.module

fun provideDomainModule() = listOf(module {
    factory { GetGithubUsersUseCase(repository = get()) }
})