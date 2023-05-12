package com.example.githubusersapi.data.repository

import com.example.githubusersapi.data.response.data
import com.example.githubusersapi.domain.entities.RepoDetailEntity
import com.example.githubusersapi.domain.entities.UserDetailEntity
import com.example.githubusersapi.domain.entities.UserEntity
import com.example.githubusersapi.domain.mapper.toEntity
import com.example.githubusersapi.domain.mapper.toRepoDetailEntity
import com.example.githubusersapi.domain.mapper.toUserDetailEntity
import com.example.githubusersapi.network.api.GithubApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GithubRepositoryImpl(private val api: GithubApi) : GithubRepository {
    override fun getUsers(): Flow<List<UserEntity>> = flow {
        emit(api.getUsers().data().toEntity())
    }

    override fun getUserDetail(login: String): Flow<UserDetailEntity> = flow {
        emit(api.getUserDetail(login).data().toUserDetailEntity())
    }

    override fun getUserRepositories(login: String): Flow<List<RepoDetailEntity>> = flow {
        emit(api.getUserRepos(login).data().toRepoDetailEntity())
    }
}