package com.example.rest.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.navArgument
import com.example.rest.feature.booking.bookingregistration.BookingRegistrationScreen
import com.example.rest.feature.booking.tables.TablesScreen
import com.example.rest.feature.starter.StarterScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController


private const val ANIMATION_DURATION = 350
private const val ANIMATION_OFFSET = 1000

@ExperimentalAnimationApi
@Composable
fun AppNavHost() {

    val navController = rememberAnimatedNavController()

    val actions = remember(navController, calculation = {
        Actions(navController = navController)
    })

    AnimatedNavHost(navController = navController, startDestination = Destinations.Starter) {

        composable(
            route = Destinations.Starter,
            content = {
                StarterScreen(
                    startBooking = actions.startBooking
                )
            }
        )
        composable(
            route = Destinations.Tables,
            content = {
                TablesScreen(
                    popBack = { navController.popBackStack() },
                    selectTable = { persons, table -> actions.selectTable.invoke(persons, table) }
                )
            },
            enterTransition = { initial, _ ->
                val route = initial.destination.route.orEmpty()

                if (!route.startsWith(prefix = Destinations.BookingRegistration)) {
                    slideInHorizontally(
                        initialOffsetX = { ANIMATION_OFFSET },
                        animationSpec = tween(durationMillis = ANIMATION_DURATION)
                    )
                } else null
            },
            popExitTransition = { _, _ ->
                slideOutHorizontally(
                    targetOffsetX = { ANIMATION_OFFSET },
                    animationSpec = tween(durationMillis = ANIMATION_DURATION)
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
            },
            enterTransition = { _, _ ->
                slideInHorizontally(
                    initialOffsetX = { ANIMATION_OFFSET },
                    animationSpec = tween(durationMillis = ANIMATION_DURATION)
                )
            },
            popExitTransition = { _, _ ->
                slideOutHorizontally(
                    targetOffsetX = { ANIMATION_OFFSET },
                    animationSpec = tween(durationMillis = ANIMATION_DURATION)
                )
            }
        )
    }
}