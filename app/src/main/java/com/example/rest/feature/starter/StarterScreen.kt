package com.example.rest.feature.starter

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun StarterScreen() {

    LazyColumn(
        content = {
            item { Toolbar() }
            item { ButtonsRow() }
        },
        modifier = Modifier
            .background(color = MaterialTheme.colors.primary)
            .fillMaxSize()
    )
}

@Composable
fun Toolbar() {

    TopAppBar(
        title = {
            Text(
                text = "VERANDA",
                color = MaterialTheme.colors.secondary,
                modifier = Modifier.fillMaxWidth(fraction = 1f),
                textAlign = TextAlign.Center
            )
        },
        modifier = Modifier
            .height(height = 44.dp)
            .fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.primary
    )
}

@Composable
fun ButtonsRow() {

    Column {
        MenuButton()
    }
}

@Composable
fun MenuButton() {

    Button(
        onClick = {

        },
        modifier = Modifier
            .background(color = MaterialTheme.colors.primary)
            .height(height = 56.dp)
            .fillMaxWidth(),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colors.primaryVariant,
        )
    ) {
        Text(
            text = "Просмотреть меню",
            fontSize = 16.sp,
            color = MaterialTheme.colors.secondary
        )
    }
}

