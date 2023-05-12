package com.example.githubusersapi.data.repository

import com.example.githubusersapi.domain.entities.RepoDetailEntity
import com.example.githubusersapi.domain.entities.UserDetailEntity
import com.example.githubusersapi.domain.entities.UserEntity
import kotlinx.coroutines.flow.Flow

interface GithubRepository {
    fun getUsers(): Flow<List<UserEntity>>
    fun getUserDetail(login: String): Flow<UserDetailEntity>
    fun getUserRepositories(login: String): Flow<List<RepoDetailEntity>>
}