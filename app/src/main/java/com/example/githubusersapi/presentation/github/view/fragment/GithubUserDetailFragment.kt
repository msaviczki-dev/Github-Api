package com.example.githubusersapi.presentation.github.view.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.githubusersapi.data.result.Result
import com.example.githubusersapi.databinding.FragmentGithubUserDetailBinding
import com.example.githubusersapi.domain.entities.RepoDetailEntity
import com.example.githubusersapi.domain.entities.UserDetailEntity
import com.example.githubusersapi.helper.base.FragmentViewBinding
import com.example.githubusersapi.helper.extension.loadImageCircle
import com.example.githubusersapi.presentation.github.view.adapter.GithubRepoDetailAdapter
import com.example.githubusersapi.presentation.github.viewmodel.GithubViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class GithubUserDetailFragment : FragmentViewBinding<FragmentGithubUserDetailBinding>(),
    GithubRepoDetailAdapter.OnGithubRepoClickListener {

    private val viewModel: GithubViewModel by sharedViewModel()

    override fun getViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentGithubUserDetailBinding =
        FragmentGithubUserDetailBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView()
        onObserveGetGithubUserDetail()
        onObserveGetGithubRepoDetail()
        initRequests()
    }

    private fun bindView() = binding.apply {
        errorDetail.btnErrorTryAgain.setOnClickListener {
            initRequests()
        }
    }

    private fun initRequests() {
        viewModel.getRepoDetail()
        viewModel.getUserDetail()
    }

    private fun onObserveGetGithubUserDetail() = lifecycleScope.launchWhenCreated {
        viewModel.userDetailFlow.collect { result ->
            when (result) {
                is Result.Loading -> onLoading()
                is Result.Error -> onError()
                is Result.Success -> onSuccess(result.data)
                else -> {}
            }
        }
    }

    private fun onObserveGetGithubRepoDetail() = lifecycleScope.launchWhenCreated {
        viewModel.repoDetailFlow.collect { result ->
            when (result) {
                is Result.Success -> onSuccessRepoDetail(result.data)
                else -> {}
            }
        }
    }

    private fun onSuccessRepoDetail(list: List<RepoDetailEntity>) = binding.apply {
        val adapter = GithubRepoDetailAdapter(list, this@GithubUserDetailFragment)
        recyclerRepos.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun onLoading() = binding.apply {
        loadingDetail.isVisible = true
        errorDetail.root.isVisible = false
    }

    private fun onError() = binding.apply {
        loadingDetail.isVisible = false
        errorDetail.root.isVisible = true
    }

    private fun onSuccess(entity: UserDetailEntity) = binding.apply {
        loadingDetail.isVisible = false
        txtUserDetailName.text = entity.name
        txtUserDetailUser.text = viewModel.login
        txtUserDetailAddress.text = entity.address
        txtUserDetailCompany.text = entity.company
        txtUserDetailFollowing.text = entity.following
        txtUserDetailFollowers.text = entity.followers
        imgUserDetailAvatar.loadImageCircle(entity.avatarUrl)
    }

    override fun openGithubLink(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }
}