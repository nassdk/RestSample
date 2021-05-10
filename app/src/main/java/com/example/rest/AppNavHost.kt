package com.example.rest

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rest.feature.starter.StarterScreen
import com.example.rest.navigation.Destinations

@Composable
fun AppNavHost() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Destinations.Starter) {
        composable(
            route = Destinations.Starter, content = {
                StarterScreen()
            }
        )
    }
}