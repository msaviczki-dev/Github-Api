package com.example.githubusersapi.domain.usecase

import com.example.githubusersapi.data.repository.GithubRepository

class GetGithubUserDetailUseCase(private val repository: GithubRepository) {
    operator fun invoke(login: String) = repository.getUserDetail(login)
}