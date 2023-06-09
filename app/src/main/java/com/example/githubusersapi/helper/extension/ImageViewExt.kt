package com.example.githubusersapi.helper.extension

import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide

fun AppCompatImageView.loadImage(url: String) {
    Glide.with(this).load(url).into(this)
}

fun AppCompatImageView.loadImageCircle(url: String) {
    Glide.with(this).load(url).circleCrop().into(this)
}