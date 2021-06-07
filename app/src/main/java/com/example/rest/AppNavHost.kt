package com.example.rest

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.rest.feature.booking.bookingregistration.BookingRegistrationScreen
import com.example.rest.feature.booking.tables.TablesScreen
import com.example.rest.feature.starter.StarterScreen
import com.example.rest.navigation.Actions
import com.example.rest.navigation.BookingRegistration
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
                TablesScreen(
                    popBack = { navController.popBackStack() },
                    selectTable = { persons, table -> actions.selectTable.invoke(persons, table) }
                )
            }
        )
        composable(
            route = Destinations.BookingRegistration,
            arguments = listOf(
                navArgument(
                    name = BookingRegistration.ARG_TABLE_NUMBER,
                    builder = { type = NavType.StringType }
                ),
                navArgument(
                    name = BookingRegistration.ARG_PERSONS_COUNT,
                    builder = { type = NavType.IntType }
                ),
            ),
            content = { backStackEntry ->
                BookingRegistrationScreen(
                    popBack = { navController.popBackStack() },
                    tableNumber = backStackEntry.arguments?.getString(BookingRegistration.ARG_TABLE_NUMBER)
                        .orEmpty(),
                    tablePersons = backStackEntry.arguments?.getInt(BookingRegistration.ARG_PERSONS_COUNT)
                        ?: 0
                )
            }
        )
    }
}