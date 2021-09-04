package com.example.rest.ui.common.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun HeaderBold16(title: String, modifier: Modifier = Modifier) {

    Text(
        text = title,
        fontSize = 16.sp,
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}