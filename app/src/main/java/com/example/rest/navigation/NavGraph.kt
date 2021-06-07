package com.example.rest.navigation

import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.example.rest.navigation.Destinations.BookingRegistration
import com.example.rest.navigation.Destinations.Tables

object Destinations {
    const val Starter = "starter"
    const val Tables = "tables"
    const val BookingRegistration = "bookingRegistration/{tableNumber}/{tablePersons}"
}

class Actions(navController: NavController) {

    val startBooking: () -> Unit = {
        navController.navigate(Tables)
    }

    val selectTable: (personCount: Int, tableNumber: String) -> Unit = { count, table ->
        navController.navigate("$BookingRegistration/$count/$table")
    }
}