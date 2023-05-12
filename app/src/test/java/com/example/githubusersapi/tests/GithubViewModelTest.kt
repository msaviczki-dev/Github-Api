package com.example.githubusersapi.tests

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.githubusersapi.data.REPOS_ENTITY
import com.example.githubusersapi.data.USER_DETAIL_ENTITY
import com.example.githubusersapi.data.USER_ENTITY
import com.example.githubusersapi.domain.entities.UserEntity
import com.example.githubusersapi.domain.usecase.GetGithubUserDetailUseCase
import com.example.githubusersapi.domain.usecase.GetGithubUserRepositoriesUseCase
import com.example.githubusersapi.domain.usecase.GetGithubUsersUseCase
import com.example.githubusersapi.helper.MainCoroutineRule
import com.example.githubusersapi.data.result.Result
import com.example.githubusersapi.domain.entities.RepoDetailEntity
import com.example.githubusersapi.domain.entities.UserDetailEntity
import com.example.githubusersapi.presentation.github.viewmodel.GithubViewModel
import com.google.common.truth.Truth
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GithubViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val detailUseCase: GetGithubUserDetailUseCase = mockk(relaxed = true)
    private val userUseCase: GetGithubUsersUseCase = mockk(relaxed = true)
    private val reposUseCase: GetGithubUserRepositoriesUseCase = mockk(relaxed = true)

    private lateinit var viewModel: GithubViewModel

    @Before
    fun setup() {
        viewModel = GithubViewModel(
            getUserUseCase = userUseCase,
            getUserDetailUseCase = detailUseCase,
            getUserRepositoriesUseCase = reposUseCase,
        )
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `when fetch getUsers data successfully should update correctly state`(): Unit =
        runBlockingTest {
            // ARRANGE
            val stateList = mutableListOf<Result<List<UserEntity>>>()
            every { userUseCase() } returns flowOf(USER_ENTITY)

            //ACT
            val job = launch {
                viewModel.userFlow.toList(stateList)
                viewModel.userFlow.collect()
            }

            viewModel.getUsers()

            // ASSERT
            Truth.assertThat(stateList.size).isEqualTo(3)
            Truth.assertThat(stateList.first()).isEqualTo(Result.Idle)
            Truth.assertThat(stateList[1]).isEqualTo(Result.Loading)
            Truth.assertThat(stateList.last()).isEqualTo(Result.Success(USER_ENTITY))

            job.cancel()
        }

    @Test
    fun `when fetch getUsers error should update correctly state`(): Unit =
        runBlockingTest {
            // ARRANGE
            val error = Throwable()
            val stateList = mutableListOf<Result<List<UserEntity>>>()
            every { userUseCase() } returns flow { throw error }

            //ACT
            val job = launch {
                viewModel.userFlow.toList(stateList)
                viewModel.userFlow.collect()
            }

            viewModel.getUsers()

            // ASSERT
            Truth.assertThat(stateList.size).isEqualTo(3)
            Truth.assertThat(stateList.first()).isEqualTo(Result.Idle)
            Truth.assertThat(stateList[1]).isEqualTo(Result.Loading)
            Truth.assertThat(stateList.last()).isEqualTo(Result.Error(error))

            job.cancel()
        }

    @Test
    fun `when fetch getUserDetail data successfully should update correctly state`(): Unit =
        runBlockingTest {
            // ARRANGE
            val stateList = mutableListOf<Result<UserDetailEntity>>()
            every { detailUseCase("logan") } returns flowOf(USER_DETAIL_ENTITY)

            //ACT
            val job = launch {
                viewModel.userDetailFlow.toList(stateList)
                viewModel.userDetailFlow.collect()
            }
            viewModel.login = "logan"
            viewModel.getUserDetail()

            // ASSERT
            Truth.assertThat(stateList.size).isEqualTo(3)
            Truth.assertThat(stateList.first()).isEqualTo(Result.Idle)
            Truth.assertThat(stateList[1]).isEqualTo(Result.Loading)
            Truth.assertThat(stateList.last()).isEqualTo(Result.Success(USER_DETAIL_ENTITY))

            job.cancel()
        }

    @Test
    fun `when fetch getUserDetail error should update correctly state`(): Unit =
        runBlockingTest {
            // ARRANGE
            val error = Throwable()
            val stateList = mutableListOf<Result<UserDetailEntity>>()
            every { detailUseCase("logan") } returns flow { throw error }

            //ACT
            val job = launch {
                viewModel.userDetailFlow.toList(stateList)
                viewModel.userDetailFlow.collect()
            }

            viewModel.login = "logan"
            viewModel.getUserDetail()

            // ASSERT
            Truth.assertThat(stateList.size).isEqualTo(3)
            Truth.assertThat(stateList.first()).isEqualTo(Result.Idle)
            Truth.assertThat(stateList[1]).isEqualTo(Result.Loading)
            Truth.assertThat(stateList.last()).isEqualTo(Result.Error(error))

            job.cancel()
        }

    @Test
    fun `when fetch getUserRepos data successfully should update correctly state`(): Unit =
        runBlockingTest {
            // ARRANGE
            val stateList = mutableListOf<Result<List<RepoDetailEntity>>>()
            every { reposUseCase("logan") } returns flowOf(REPOS_ENTITY)

            //ACT
            val job = launch {
                viewModel.repoDetailFlow.toList(stateList)
                viewModel.repoDetailFlow.collect()
            }

            viewModel.login = "logan"
            viewModel.getRepoDetail()

            // ASSERT
            Truth.assertThat(stateList.size).isEqualTo(3)
            Truth.assertThat(stateList.first()).isEqualTo(Result.Idle)
            Truth.assertThat(stateList[1]).isEqualTo(Result.Loading)
            Truth.assertThat(stateList.last()).isEqualTo(Result.Success(REPOS_ENTITY))

            job.cancel()
        }

    @Test
    fun `when fetch getUserRepos error should update correctly state`(): Unit =
        runBlockingTest {
            // ARRANGE
            val error = Throwable()
            val stateList = mutableListOf<Result<List<RepoDetailEntity>>>()
            every { reposUseCase("logan") } returns flow { throw error }

            //ACT
            val job = launch {
                viewModel.repoDetailFlow.toList(stateList)
                viewModel.repoDetailFlow.collect()
            }

            viewModel.login = "logan"
            viewModel.getRepoDetail()

            // ASSERT
            Truth.assertThat(stateList.size).isEqualTo(3)
            Truth.assertThat(stateList.first()).isEqualTo(Result.Idle)
            Truth.assertThat(stateList[1]).isEqualTo(Result.Loading)
            Truth.assertThat(stateList.last()).isEqualTo(Result.Error(error))

            job.cancel()
        }
}