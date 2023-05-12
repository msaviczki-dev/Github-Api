package com.example.githubusersapi.domain.entities

data class UserDetailEntity(
    val name: String,
    val company: String,
    val address: String,
    val avatarUrl: String,
    val followers: String,
    val following: String
)