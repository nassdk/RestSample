package com.example.rest.feature.starter

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rest.R

const val ARG_IS_BOOKING = "ARG_IS_BOOKING"

@Composable
fun StarterScreen(
    startBooking: () -> Unit,
    makeDeliveryClick: () -> Unit
) {

    Column(
        content = {
            Toolbar()
            ButtonsColumn(
                bookingClick = startBooking,
                makeDeliveryClick = makeDeliveryClick
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.primary)
    )
}

@Composable
private fun Toolbar() {

    TopAppBar(
        title = {

            Box(
                content = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_logo),
                        contentDescription = "LogoIcon",
                        modifier = Modifier
                            .height(height = 30.dp)
                            .width(width = 100.dp)
                            .align(alignment = Alignment.Center)
                    )

                    Button(
                        onClick = {},
                        modifier = Modifier.align(alignment = Alignment.TopEnd)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_notifications_bell),
                            contentDescription = "BellIcon"
                        )
                    }
                },
                modifier = Modifier.fillMaxSize()
            )
        },
        modifier = Modifier
            .height(height = 44.dp)
            .fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.primary
    )
}

@Composable
private fun ButtonsColumn(
    bookingClick: () -> Unit,
    makeDeliveryClick: () -> Unit
) {

    Column(
        content = {
            MakeDeliveryButton(onClick = makeDeliveryClick)

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
            )

            FeedbackButton()

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
            )

            MakeBookingButton(
                bookingClick = bookingClick
            )
        },
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    )
}

@Composable
private fun MakeDeliveryButton(onClick: () -> Unit) {

    Button(
        onClick = onClick,
        modifier = Modifier
            .background(color = MaterialTheme.colors.primary)
            .height(height = 56.dp)
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colors.primaryVariant,
        ),
        shape = RoundedCornerShape(size = 4.dp)
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_menu),
            contentDescription = "MenuIcon"
        )

        Text(
            text = "Оформить доставку",
            fontSize = 16.sp,
            color = MaterialTheme.colors.secondary,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .weight(weight = 1f)
                .padding(start = 8.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.ic_chevron_right_black),
            contentDescription = "Chevron"
        )
    }
}

@Composable
private fun FeedbackButton() {

    Button(
        onClick = {

        },
        modifier = Modifier
            .background(color = MaterialTheme.colors.primary)
            .height(height = 56.dp)
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colors.primaryVariant,
        ),
        shape = RoundedCornerShape(size = 4.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_reviews),
            contentDescription = "ReviewsIcon"
        )

        Text(
            text = "Жалобная книга",
            fontSize = 16.sp,
            color = MaterialTheme.colors.secondary,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .weight(weight = 1f)
                .padding(start = 8.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.ic_chevron_right_black),
            contentDescription = "Chevron"
        )
    }
}

@Composable
private fun MakeBookingButton(
    bookingClick: () -> Unit
) {

    Button(
        onClick = {
            bookingClick.invoke()
        },
        modifier = Modifier
            .indication(
                indication = rememberRipple(bounded = true),
                interactionSource = remember { MutableInteractionSource() })
            .background(color = MaterialTheme.colors.primary)
            .height(height = 56.dp)
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colors.primaryVariant,
        ),
        shape = RoundedCornerShape(size = 4.dp)
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_booking),
            contentDescription = "BookingIcon"
        )

        Text(
            text = "Забронировать столик",
            fontSize = 16.sp,
            color = MaterialTheme.colors.secondary,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .weight(weight = 1f)
                .padding(start = 8.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.ic_chevron_right_black),
            contentDescription = "Chevron"
        )
    }
}

