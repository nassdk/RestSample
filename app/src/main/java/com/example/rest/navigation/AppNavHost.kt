package com.example.rest.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.navArgument
import com.example.rest.feature.bookingregistration.ARG_PERSONS_COUNT
import com.example.rest.feature.bookingregistration.ARG_TABLE_NUMBER
import com.example.rest.feature.bookingregistration.BookingRegistrationScreen
import com.example.rest.feature.ordersuccess.OrderSuccessScreen
import com.example.rest.feature.starter.ARG_IS_BOOKING
import com.example.rest.feature.starter.StarterScreen
import com.example.rest.feature.tables.TablesScreen
import com.example.rest.utils.orFalse
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
            route = Destinations.BookingRegistration.plus("/{${ARG_TABLE_NUMBER}}/{${ARG_PERSONS_COUNT}}"),
            arguments = listOf(
                navArgument(
                    name = ARG_TABLE_NUMBER,
                    builder = { type = NavType.StringType }
                ),
                navArgument(
                    name = ARG_PERSONS_COUNT,
                    builder = { type = NavType.StringType }
                )
            ),
            content = { backStackEntry ->

                val tableNumber = backStackEntry.arguments
                    ?.getString(ARG_TABLE_NUMBER)
                    .orEmpty()

                val personCount = backStackEntry.arguments
                    ?.getString(ARG_PERSONS_COUNT)
                    .orEmpty()

                BookingRegistrationScreen(
                    popBack = { navController.popBackStack() },
                    finishBooking = { actions.successBooking() },
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
        composable(
            route = Destinations.OrderThanks.plus("/{${ARG_IS_BOOKING}}"),
            arguments = listOf(
                navArgument(
                    name = ARG_IS_BOOKING,
                    builder = { type = NavType.BoolType }
                )
            ),
            content = { navBackStackEntry ->

                val isBooking = navBackStackEntry.arguments
                    ?.getBoolean(ARG_IS_BOOKING)
                    .orFalse()

                OrderSuccessScreen(
                    isBooking = isBooking,
                    toHomeClicked = {
                        navController.popBackStack()
                    }
                )

            },
            enterTransition = { _, _ ->
                slideInVertically(
                    initialOffsetY = { ANIMATION_OFFSET },
                    animationSpec = tween(durationMillis = ANIMATION_DURATION)
                )
            },
            popExitTransition = { _, _ ->
                slideOutVertically(
                    targetOffsetY = { ANIMATION_OFFSET },
                    animationSpec = tween(durationMillis = ANIMATION_DURATION)
                )
            }
        )
    }
}