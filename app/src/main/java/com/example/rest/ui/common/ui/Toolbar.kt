package com.example.rest.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rest.R

@Composable
fun ToolbarBase(
    navigationClickListener: () -> Unit,
    title: String
) {

    TopAppBar(
        title = {
            Text(
                text = title,
                fontSize = 16.sp,
                color = MaterialTheme.colors.secondary,
                modifier = Modifier.fillMaxWidth()
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { navigationClickListener.invoke() },
                content = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_arrow_left_black),
                        contentDescription = "ArrowBack"
                    )
                }
            )
        },
        modifier = Modifier
            .height(height = 44.dp)
            .fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.primary
    )
}