package com.example.githubusersapi.di

class ModuleProvider {
    fun provide() = listOf(
        provideDataModule() + providePresentationModule() + provideDomainModule()
    ).flatten()
}