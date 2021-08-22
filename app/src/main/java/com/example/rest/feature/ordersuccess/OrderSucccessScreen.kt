package com.example.rest.feature.ordersuccess

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rest.R

@Composable
fun OrderSuccessScreen(isBooking: Boolean, toHomeClicked: () -> Unit) {

    val titleText = if (isBooking) "Спасибо за бронирование" else "Спасибо за заказ"
    val messageText =
        if (isBooking) "Ждем вас в нашем заведении" else "Ваш заказ вы можете посмотреть на экране “Покупки”"

    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colors.primary)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        content = {

            Image(
                painter = painterResource(id = R.drawable.ic_heart_thanks),
                contentDescription = "ic_heart_thanks"
            )

            Text(
                text = titleText,
                color = Color.Black,
                fontSize = 16.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 16.dp)
            )

            Text(
                text = messageText,
                color = Color.Gray,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 12.dp)
            )

            Button(
                onClick = toHomeClicked,
                modifier = Modifier.padding(top = 32.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
                content = {
                    Text(
                        text = "На главную",
                        color = Color.White,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 54.dp)
                    )
                }
            )
        }
    )
}
