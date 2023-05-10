package com.example.githubusersapi.domain.entities

data class UserEntity(
    val user: String,
    val avatarUrl: String,
    val isAdmin: Boolean,
    val type: String
)