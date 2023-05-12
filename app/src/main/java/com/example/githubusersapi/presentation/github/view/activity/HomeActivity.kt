package com.example.githubusersapi.presentation.github.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.githubusersapi.databinding.ActivityHomeBinding
import com.example.githubusersapi.presentation.github.viewmodel.GithubViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val viewModel: GithubViewModel by viewModel()

    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}