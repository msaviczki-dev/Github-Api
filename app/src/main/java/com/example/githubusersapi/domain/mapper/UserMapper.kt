package com.example.githubusersapi.domain.mapper

import com.example.githubusersapi.data.response.RepoDetailResponse
import com.example.githubusersapi.data.response.UserDetailResponse
import com.example.githubusersapi.data.response.UserResponse
import com.example.githubusersapi.domain.entities.RepoDetailEntity
import com.example.githubusersapi.domain.entities.UserDetailEntity
import com.example.githubusersapi.domain.entities.UserEntity

fun List<UserResponse>.toEntity(): List<UserEntity> {
    val list = mutableListOf<UserEntity>()
    map { response ->
        list.add(
            UserEntity(
                user = response.login.orEmpty(),
                avatarUrl = response.avatarUrl.orEmpty(),
                isAdmin = response.siteAdmin ?: false,
                type = response.type.orEmpty()
            )
        )
    }
    return list
}

fun UserDetailResponse.toUserDetailEntity(): UserDetailEntity = UserDetailEntity(
    name = name.orEmpty(),
    company = company.orEmpty(),
    address = location.orEmpty(),
    avatarUrl = avatarUrl.orEmpty(),
    followers = (followers ?: 0).toString(),
    following = (following ?: 0).toString()
)

fun List<RepoDetailResponse>.toRepoDetailEntity(): List<RepoDetailEntity> {
    val list = mutableListOf<RepoDetailEntity>()
    map { response ->
        list.add(
            RepoDetailEntity(
                repoName = response.name.orEmpty(),
                repoUrl = response.url.orEmpty(),
                language = response.language.orEmpty(),
                fork = (response.fork ?: 0).toString(),
                starsCount = (response.starCount ?: 0).toString(),
                description = response.description.orEmpty(),
                isPrivate = if (response.private == true) "PRIVATE" else "PUBLIC"
            )
        )
    }
    return list
}