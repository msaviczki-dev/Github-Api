package com.example.githubusersapi.presentation.github.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.githubusersapi.databinding.FragmentGithubUsersBinding
import com.example.githubusersapi.helper.base.FragmentViewBinding
import com.example.githubusersapi.presentation.github.viewmodel.GithubViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import com.example.githubusersapi.data.result.Result
import com.example.githubusersapi.domain.entities.UserEntity
import com.example.githubusersapi.presentation.github.view.adapter.GithubUsersAdapter

class GithubUsersFragment : FragmentViewBinding<FragmentGithubUsersBinding>() {

    private val viewModel: GithubViewModel by sharedViewModel()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentGithubUsersBinding = FragmentGithubUsersBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews()
        onObserveGetGithubUsers()
        viewModel.getUsers()
    }

    private fun bindViews() = binding.apply {
        errorUsers.btnErrorTryAgain.setOnClickListener { viewModel.getUsers() }
    }

    private fun onObserveGetGithubUsers() = lifecycleScope.launchWhenCreated {
        viewModel.userFlow.collect { result ->
            when (result) {
                is Result.Loading -> onLoading()
                is Result.Error -> onError()
                is Result.Success -> onSuccess(result.data)
                else -> {}
            }
        }
    }

    private fun onLoading() = binding.apply {
        loadingUsers.isVisible = true
        recyclerUsers.isVisible = false
        errorUsers.root.isVisible = false
    }

    private fun onError() = binding.apply {
        loadingUsers.isVisible = false
        errorUsers.root.isVisible = false
    }

    private fun onSuccess(result: List<UserEntity>) = binding.apply {
        loadingUsers.isVisible = false
        recyclerUsers.isVisible = true
        val adapter = GithubUsersAdapter(result)
        recyclerUsers.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}