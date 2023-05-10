package com.example.githubusersapi.di

import com.example.githubusersapi.data.repository.GithubRepository
import com.example.githubusersapi.data.repository.GithubRepositoryImpl
import com.example.githubusersapi.network.api.GithubApi
import com.example.githubusersapi.network.core.ApiClient
import org.koin.dsl.module

fun provideDataModule() = listOf(module {
    // API
    single { ApiClient().client.create(GithubApi::class.java) }

    //  REPOSITORY
    single<GithubRepository> { GithubRepositoryImpl(api = get()) }
})