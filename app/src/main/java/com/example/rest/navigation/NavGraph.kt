package com.example.rest.navigation

import androidx.navigation.NavController
import com.example.rest.navigation.Destinations.BookingRegistration
import com.example.rest.navigation.Destinations.Tables

object Destinations {
    const val Starter = "starter"
    const val Tables = "tables"
    const val BookingRegistration = "bookingRegistration"
}

class Actions(navController: NavController) {

    val startBooking: () -> Unit = {
        navController.navigate(route = Tables)
    }

    val selectTable: (personCount: Int, tableNumber: String) -> Unit = { count, table ->
        navController.navigate(route = BookingRegistration.plus("/$table/$count"))
    }
}