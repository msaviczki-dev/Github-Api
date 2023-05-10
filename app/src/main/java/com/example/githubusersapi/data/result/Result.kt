package com.example.githubusersapi.data.result

sealed class Result<out D> {
    data class Success<R>(val data: R) : Result<R>()
    data class Error(val message: Throwable) : Result<Nothing>()
    object Loading : Result<Nothing>()
    object Idle : Result<Nothing>()
}