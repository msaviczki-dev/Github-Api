package com.example.githubusersapi.presentation.github.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubusersapi.domain.entities.UserEntity
import com.example.githubusersapi.data.result.Result
import com.example.githubusersapi.domain.usecase.GetGithubUsersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class GithubViewModel(private val getUserUseCase: GetGithubUsersUseCase) : ViewModel() {

    private val _userFlow = MutableStateFlow<Result<List<UserEntity>>>(Result.Idle)
    val userFlow: StateFlow<Result<List<UserEntity>>> get() = _userFlow

    fun getUsers() {
        viewModelScope.launch {
            getUserUseCase().onStart { _userFlow.value = Result.Loading }
                .catch { error -> _userFlow.value = Result.Error(error) }
                .collect { result -> _userFlow.value = Result.Success(result) }
        }
    }
}