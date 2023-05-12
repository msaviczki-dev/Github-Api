package com.example.githubusersapi.data

import com.example.githubusersapi.data.response.RepoDetailResponse
import com.example.githubusersapi.data.response.UserDetailResponse
import com.example.githubusersapi.data.response.UserResponse
import com.example.githubusersapi.domain.entities.RepoDetailEntity
import com.example.githubusersapi.domain.entities.UserDetailEntity
import com.example.githubusersapi.domain.entities.UserEntity

val USER_RESPONSE = listOf(
    UserResponse(
        login = "saviczki",
        avatarUrl = "https://avatars.githubusercontent.com/u/80161811?v=4",
        siteAdmin = false,
        type = "PRIVATE",
        nodeId = "asdasd",
        gravatarId = "213123",
        url = "https://www.google.com.br",
        htmlUrl = "htmlUrl",
        followersUrl = "",
        followingUrl = "",
        gistsUrl = "",
        starredUrl = "",
        subscriptionsUrl = "",
        organizationsUrl = "",
        reposUrl = "",
        eventsUrl = "",
        receivedEventsUrl = ""
    )
)

val USER_ENTITY = listOf(
    UserEntity(
        user = "saviczki",
        avatarUrl = "https://avatars.githubusercontent.com/u/80161811?v=4",
        isAdmin = false,
        type = "PRIVATE"
    )
)

val REPOS_RESPONSE = listOf(
    RepoDetailResponse(
        name = "github-api",
        url = "https://github.com/msaviczki-dev/Github-Api",
        language = "kotlin",
        fork = 123,
        starCount = 123,
        description = "sdfasdfasdf",
        private = true,
        fullName = "",
        id = 123,
        nodeId = "123",
        owner = RepoDetailResponse.RepoOwner(
            login = "saviczki",
            id = 123,
            nodeId = "",
            avatarUrl = "",
            gravatarId = "",
            url = ""
        )
    )
)

val REPOS_ENTITY = listOf(
    RepoDetailEntity(
        repoName = "github-api",
        repoUrl = "https://github.com/msaviczki-dev/Github-Api",
        language = "kotlin",
        fork = "123",
        starsCount = "123",
        description = "sdfasdfasdf",
        isPrivate = "PRIVATE"
    )
)

val USER_DETAIL_RESPONSE = UserDetailResponse(
    name = "Matheus Saviczki",
    company = "M.Saviczki S.A",
    location = "JUINA-MT",
    avatarUrl = "https://avatars.githubusercontent.com/u/80161811?v=4",
    followers = 123,
    following = 123,
    login = "logan",
    nodeId = "",
    gravatarId = "",
    url = "",
    htmlUrl = "",
    gistsUrl = "",
    starredUrl = "",
    subscriptionsUrl = "",
    organizationsUrl = "",
    reposUrl = "",
    eventsUrl = "",
    receivedEventsUrl = "",
    bio = "",
    blog = "",
    createDate = "",
    updateDate = "",
    email = "",
    followingUrl = "",
    followersUrl = "",
    gists = 123,
    hireable = "",
    repos = 123,
    siteAdmin = false,
    twitter = "",
    type = "USER"
)

val USER_DETAIL_ENTITY = UserDetailEntity(
    name = "Matheus Saviczki",
    company = "M.Saviczki S.A",
    address = "JUINA-MT",
    avatarUrl = "https://avatars.githubusercontent.com/u/80161811?v=4",
    followers = "123",
    following = "123"
)