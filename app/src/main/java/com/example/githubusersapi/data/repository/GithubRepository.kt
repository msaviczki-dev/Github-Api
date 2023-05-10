package com.example.githubusersapi.data.repository

import com.example.githubusersapi.domain.entities.UserEntity
import kotlinx.coroutines.flow.Flow

interface GithubRepository {
    fun getUsers(): Flow<List<UserEntity>>
}