package com.example.rest.navigation

import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.example.rest.navigation.Destinations.Tables

object Destinations {
    const val Starter = "starter"
    const val Tables = "tables"
}

class Actions(navController: NavController) {

    val startBooking: () -> Unit = {
        navController.navigate(Tables)
    }
}