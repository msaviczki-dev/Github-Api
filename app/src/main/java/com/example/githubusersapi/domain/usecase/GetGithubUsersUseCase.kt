package com.example.githubusersapi.domain.usecase

import com.example.githubusersapi.data.repository.GithubRepository

class GetGithubUsersUseCase(private val repository: GithubRepository) {
    operator fun invoke() = repository.getUsers()
}