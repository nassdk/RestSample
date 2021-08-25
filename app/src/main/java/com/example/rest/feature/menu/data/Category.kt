package com.example.rest.feature.menu.data

import androidx.annotation.DrawableRes
import com.example.rest.R

data class Category(
    @DrawableRes val icon: Int = R.drawable.ic_category,
    val title: String
)
