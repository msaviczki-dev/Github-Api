package com.example.githubusersapi.presentation.github.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubusersapi.domain.entities.UserEntity
import com.example.githubusersapi.data.result.Result
import com.example.githubusersapi.domain.entities.RepoDetailEntity
import com.example.githubusersapi.domain.entities.UserDetailEntity
import com.example.githubusersapi.domain.usecase.GetGithubUserDetailUseCase
import com.example.githubusersapi.domain.usecase.GetGithubUserRepositoriesUseCase
import com.example.githubusersapi.domain.usecase.GetGithubUsersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class GithubViewModel(
    private val getUserUseCase: GetGithubUsersUseCase,
    private val getUserDetailUseCase: GetGithubUserDetailUseCase,
    private val getUserRepositoriesUseCase: GetGithubUserRepositoriesUseCase
) : ViewModel() {

    var login = ""

    private val _userFlow = MutableStateFlow<Result<List<UserEntity>>>(Result.Idle)
    val userFlow: StateFlow<Result<List<UserEntity>>> get() = _userFlow

    private val _userDetailFlow = MutableStateFlow<Result<UserDetailEntity>>(Result.Idle)
    val userDetailFlow: StateFlow<Result<UserDetailEntity>> get() = _userDetailFlow

    private val _repoDetailFlow = MutableStateFlow<Result<List<RepoDetailEntity>>>(Result.Idle)
    val repoDetailFlow: StateFlow<Result<List<RepoDetailEntity>>> get() = _repoDetailFlow

    fun getUsers() {
        viewModelScope.launch {
            getUserUseCase().onStart { _userFlow.value = Result.Loading }
                .catch { error -> _userFlow.value = Result.Error(error) }
                .collect { result -> _userFlow.value = Result.Success(result) }
        }
    }

    fun getUserDetail() {
        viewModelScope.launch {
            getUserDetailUseCase(login).onStart { _userDetailFlow.value = Result.Loading }
                .catch { error -> _userDetailFlow.value = Result.Error(error) }
                .collect { result -> _userDetailFlow.value = Result.Success(result) }
        }
    }

    fun getRepoDetail() {
        viewModelScope.launch {
            getUserRepositoriesUseCase(login).onStart { _repoDetailFlow.value = Result.Loading }
                .catch { error -> _repoDetailFlow.value = Result.Error(error) }
                .collect { result -> _repoDetailFlow.value = Result.Success(result) }
        }
    }
}