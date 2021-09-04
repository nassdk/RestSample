package com.example.rest.ui.common

import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun CustomDividerView(modifier: Modifier = Modifier) {

    Divider(
        color = Color.LightGray,
        modifier = modifier
    )
}