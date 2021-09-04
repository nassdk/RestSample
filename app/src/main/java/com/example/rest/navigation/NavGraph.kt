package com.example.rest.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.example.rest.navigation.Destinations.BookingRegistration
import com.example.rest.navigation.Destinations.Menu
import com.example.rest.navigation.Destinations.Order
import com.example.rest.navigation.Destinations.OrderThanks
import com.example.rest.navigation.Destinations.ProductDetail
import com.example.rest.navigation.Destinations.Tables

object Destinations {
    const val Starter = "starter"
    const val Tables = "tables"
    const val BookingRegistration = "bookingRegistration"
    const val OrderThanks = "orderThanks"
    const val Menu = "menu"
    const val ProductDetail = "productDetail"
    const val Order = "order"
}

class Actions(navController: NavController) {

    val startBooking: () -> Unit = {
        navController.navigate(route = Tables)
    }

    val selectTable: (personCount: Int, tableNumber: String) -> Unit = { count, table ->
        navController.navigate(route = BookingRegistration.plus("/$table/$count"))
    }

    val successBooking: () -> Unit = {
        navController.navigate(
            route = OrderThanks.plus("/true"),
            navOptions = NavOptions.Builder()
                .setPopUpTo(route = Destinations.Starter, inclusive = false, saveState = false)
                .build()
        )
    }

    val openMenu: () -> Unit = {
        navController.navigate(route = Menu)
    }

    val toProductDetail: () -> Unit = {
        navController.navigate(route = ProductDetail)
    }

    val toOrder: () -> Unit = {
        navController.navigate(route = Order)
    }
}