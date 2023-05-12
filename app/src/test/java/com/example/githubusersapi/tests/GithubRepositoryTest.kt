package com.example.githubusersapi.tests

import com.example.githubusersapi.data.*
import com.example.githubusersapi.data.repository.GithubRepositoryImpl
import com.example.githubusersapi.network.api.GithubApi
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class GithubRepositoryTest {

    private val api: GithubApi = mockk(relaxed = true)

    private var repository = GithubRepositoryImpl(api)

    @Test
    fun `when get success return on getUsers api`() = runBlocking {

        // ARRANGE
        coEvery { api.getUsers() } returns Response.success(USER_RESPONSE)

        // ACT
        repository.getUsers().collect {

            // ASSERT
            Truth.assertThat(it).isEqualTo(USER_ENTITY)
            coVerify(exactly = 1) { api.getUsers() }
        }
    }

    @Test
    fun `when get success return on getUserDetail api`() = runBlocking {

        // ARRANGE
        coEvery { api.getUserDetail(login = "logan") } returns Response.success(USER_DETAIL_RESPONSE)

        // ACT
        repository.getUserDetail("logan").collect {

            // ASSERT
            Truth.assertThat(it).isEqualTo(USER_DETAIL_ENTITY)
            coVerify(exactly = 1) { api.getUserDetail(login = "logan") }
        }
    }

    @Test
    fun `when get success return on getRepos api`() = runBlocking {

        // ARRANGE
        coEvery { api.getUserRepos(login = "logan") } returns Response.success(REPOS_RESPONSE)

        // ACT
        repository.getUserRepositories("logan").collect {

            // ASSERT
            Truth.assertThat(it).isEqualTo(REPOS_ENTITY)
            coVerify(exactly = 1) { api.getUserRepos(login = "logan") }
        }
    }
}