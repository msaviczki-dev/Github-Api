package com.example.githubusersapi.domain.entities

data class RepoDetailEntity(
    val repoName: String,
    val repoUrl: String,
    val language: String,
    val fork: String,
    val starsCount: String,
    val description: String,
    val isPrivate: String
)