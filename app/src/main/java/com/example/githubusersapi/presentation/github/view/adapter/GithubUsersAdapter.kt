package com.example.githubusersapi.presentation.github.view.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubusersapi.databinding.UserItemBinding
import com.example.githubusersapi.domain.entities.UserEntity
import com.example.githubusersapi.helper.extension.loadImageCircle

private const val ADMIN_USER = "YES"
private const val NO_ADMIN_USER = "NO"
private const val GREY_COLOR = "#e6e6e6"

class GithubUsersAdapter(private val list: List<UserEntity>) :
    RecyclerView.Adapter<GithubUsersAdapter.GithubUsersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubUsersViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GithubUsersViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: GithubUsersViewHolder, position: Int) {
        holder.bind(list[position], position)
    }

    inner class GithubUsersViewHolder(private val binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(entity: UserEntity, position: Int) = binding.apply {
            val backgroundColor =
                if (position % 2 == 0) Color.WHITE else Color.parseColor(GREY_COLOR)
            txtUserName.text = entity.user
            txtUserType.text = entity.type
            txtUserAdmin.text = if (entity.isAdmin) ADMIN_USER else NO_ADMIN_USER
            imgUserAvatar.loadImageCircle(entity.avatarUrl)
            root.setBackgroundColor(backgroundColor)
        }
    }
}