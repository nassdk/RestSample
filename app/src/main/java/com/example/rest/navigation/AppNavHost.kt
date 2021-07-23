package com.example.rest.navigation

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
            route = Destinations.BookingRegistration.plus("/{${Args.BookingRegistration.ARG_TABLE_NUMBER}}/{${Args.BookingRegistration.ARG_PERSONS_COUNT}}"),
            arguments = listOf(
                navArgument(
                    name = Args.BookingRegistration.ARG_TABLE_NUMBER,
                    builder = { type = NavType.StringType }
                ),
                navArgument(
                    name = Args.BookingRegistration.ARG_PERSONS_COUNT,
                    builder = { type = NavType.StringType }
                )
            ),
            content = { backStackEntry ->

                val tableNumber = backStackEntry.arguments
                    ?.getString(Args.BookingRegistration.ARG_TABLE_NUMBER)
                    .orEmpty()

                val personCount = backStackEntry.arguments
                    ?.getString(Args.BookingRegistration.ARG_PERSONS_COUNT)
                    .orEmpty()

                BookingRegistrationScreen(
                    popBack = { navController.popBackStack() },
                    tableNumber = tableNumber,
                    tablePersons = personCount
                )
            }
        )
    }
}