package com.example.githubusersapi.network.api

import com.example.githubusersapi.data.response.UserResponse
import retrofit2.Response
import retrofit2.http.GET

interface GithubApi {
    @GET("users")
    suspend fun getUsers(): Response<List<UserResponse>>
}