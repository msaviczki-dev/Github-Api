package com.example.githubusersapi.network.api

import com.example.githubusersapi.data.response.RepoDetailResponse
import com.example.githubusersapi.data.response.UserDetailResponse
import com.example.githubusersapi.data.response.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {
    @GET("users")
    suspend fun getUsers(): Response<List<UserResponse>>

    @GET("users/{login}")
    suspend fun getUserDetail(@Path("login") login: String): Response<UserDetailResponse>

    @GET("users/{login}/repos")
    suspend fun getUserRepos(@Path("login") login: String): Response<List<RepoDetailResponse>>
}