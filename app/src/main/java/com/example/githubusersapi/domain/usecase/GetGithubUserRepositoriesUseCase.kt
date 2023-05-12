package com.example.githubusersapi.domain.usecase

import com.example.githubusersapi.data.repository.GithubRepository

class GetGithubUserRepositoriesUseCase(private val repository: GithubRepository) {
    operator fun invoke(login: String) = repository.getUserRepositories(login)
}