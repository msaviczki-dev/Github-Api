package com.example.githubusersapi.data.response

import com.google.gson.annotations.SerializedName

data class RepoDetailResponse(
    @SerializedName("id") val id: Int?,
    @SerializedName("node_id") val nodeId: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("full_name") val fullName: String?,
    @SerializedName("private") val private: Boolean?,
    @SerializedName("language") val language: String?,
    @SerializedName("html_url") val url: String?,
    @SerializedName("forks_count") val fork: Int?,
    @SerializedName("stargazers_count") val starCount: Int?,
    @SerializedName("description") val description: String?,
    @SerializedName("owner") val owner: RepoOwner
) {
    data class RepoOwner(
        @SerializedName("login") val login: String?,
        @SerializedName("id") val id: Int?,
        @SerializedName("node_id") val nodeId: String?,
        @SerializedName("avatar_url") val avatarUrl: String?,
        @SerializedName("gravatar_id") val gravatarId: String?,
        @SerializedName("url") val url: String?
    )
}