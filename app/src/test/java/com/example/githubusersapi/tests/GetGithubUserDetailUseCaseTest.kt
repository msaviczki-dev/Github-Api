package com.example.githubusersapi.tests

import com.example.githubusersapi.data.USER_DETAIL_ENTITY
import com.example.githubusersapi.data.repository.GithubRepository
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
class GetGithubUserDetailUseCaseTest {
    private val repository: GithubRepository = mockk(relaxed = true)

    private var useCase =
        com.example.githubusersapi.domain.usecase.GetGithubUserDetailUseCase(repository)

    @Test
    fun `when get success return on getUserDetail repository`() = runBlocking {

        // ARRANGE
        coEvery { repository.getUserDetail("logan") } returns flowOf(USER_DETAIL_ENTITY)

        // ACT
        useCase("logan").collect {

            // ASSERT
            Truth.assertThat(it).isEqualTo(USER_DETAIL_ENTITY)
            coVerify(exactly = 1) { repository.getUserDetail("logan") }
        }
    }

    @Test(expected = Throwable::class)
    fun `when get error return on getUserDetail repository`() = runBlocking {

        // ARRANGE
        coEvery { repository.getUserDetail("logan") } returns flow { throw Throwable() }

        // ACT
        useCase("logan").collect()
    }
}