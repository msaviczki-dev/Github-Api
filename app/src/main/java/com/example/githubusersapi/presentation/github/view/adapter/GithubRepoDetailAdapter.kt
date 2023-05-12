package com.example.githubusersapi.presentation.github.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubusersapi.databinding.RepoDetailItemBinding
import com.example.githubusersapi.domain.entities.RepoDetailEntity

class GithubRepoDetailAdapter(
    private val list: List<RepoDetailEntity>,
    private val listener: OnGithubRepoClickListener
) :
    RecyclerView.Adapter<GithubRepoDetailAdapter.GithubRepoDetailViewHolder>() {

    inner class GithubRepoDetailViewHolder(private val binding: RepoDetailItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RepoDetailEntity) = binding.apply {
            txtRepoName.text = item.repoName
            txtRepoLanguage.text = item.language
            txtRepoPrivate.text = item.isPrivate
            txtRepoDetail.text = item.description
            txtRepoFavorite.text = item.starsCount
            binding.root.setOnClickListener { listener.openGithubLink(item.repoUrl) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubRepoDetailViewHolder {
        val binding =
            RepoDetailItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GithubRepoDetailViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: GithubRepoDetailViewHolder, position: Int) {
        holder.bind(list[position])
    }

    interface OnGithubRepoClickListener {
        fun openGithubLink(url: String)
    }
}