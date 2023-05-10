package com.example.githubusersapi.domain.mapper

import com.example.githubusersapi.data.response.UserResponse
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