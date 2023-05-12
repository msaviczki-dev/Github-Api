package com.example.githubusersapi.tests

import com.example.githubusersapi.data.REPOS_ENTITY
import com.example.githubusersapi.data.USER_DETAIL_ENTITY
import com.example.githubusersapi.data.repository.GithubRepository
import com.example.githubusersapi.domain.usecase.GetGithubUserRepositoriesUseCase
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test

@ExperimentalCoroutinesApi
class GetGithubUserRepositoriesUseCaseTest {
    private val repository: GithubRepository = mockk(relaxed = true)

    private var useCase = GetGithubUserRepositoriesUseCase(repository)

    @Test
    fun `when get success return on getUserDetail repository`() = runBlocking {

        // ARRANGE
        coEvery { repository.getUserRepositories("logan") } returns flowOf(REPOS_ENTITY)

        // ACT
        useCase("logan").collect {

            // ASSERT
            Truth.assertThat(it).isEqualTo(REPOS_ENTITY)
            coVerify(exactly = 1) { repository.getUserRepositories("logan") }
        }
    }

    @Test(expected = Throwable::class)
    fun `when get error return on getUserDetail repository`() = runBlocking {

        // ARRANGE
        coEvery { repository.getUserRepositories("logan") } returns flow { throw Throwable() }

        // ACT
        useCase("logan").collect()
    }
}