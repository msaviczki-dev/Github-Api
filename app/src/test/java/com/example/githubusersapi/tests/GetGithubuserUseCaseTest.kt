package com.example.githubusersapi.tests

import com.example.githubusersapi.data.USER_ENTITY
import com.example.githubusersapi.data.repository.GithubRepository
import com.example.githubusersapi.domain.usecase.GetGithubUsersUseCase
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
class GetGithubuserUseCaseTest {

    private val repository: GithubRepository = mockk(relaxed = true)

    private var useCase = GetGithubUsersUseCase(repository)

    @Test
    fun `when get success return on getUsers repository`() = runBlocking {

        // ARRANGE
        coEvery { repository.getUsers() } returns flowOf(USER_ENTITY)

        // ACT
        useCase().collect {

            // ASSERT
            Truth.assertThat(it).isEqualTo(USER_ENTITY)
            coVerify(exactly = 1) { repository.getUsers() }
        }
    }

    @Test(expected = Throwable::class)
    fun `when get error return on getUsers repository`() = runBlocking {

        // ARRANGE
        coEvery { repository.getUsers() } returns flow { throw Throwable() }

        // ACT
        useCase().collect()
    }
}