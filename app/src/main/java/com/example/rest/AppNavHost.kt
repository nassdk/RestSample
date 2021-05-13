package com.example.rest

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rest.feature.booking.tables.TablesScreen
import com.example.rest.feature.starter.StarterScreen
import com.example.rest.navigation.Actions
import com.example.rest.navigation.Destinations

@Composable
fun AppNavHost() {

    val navController = rememberNavController()

    val actions = remember(navController, calculation = {
        Actions(navController = navController)
    })

    NavHost(navController = navController, startDestination = Destinations.Starter) {

        composable(
            route = Destinations.Starter, content = {
                StarterScreen(
                    startBooking = actions.startBooking
                )
            }
        )
        composable(
            route = Destinations.Tables, content = {
                TablesScreen()
            }
        )
    }
}