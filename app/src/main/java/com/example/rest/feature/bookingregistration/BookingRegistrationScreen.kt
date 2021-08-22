package com.example.rest.feature.bookingregistration

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rest.R

@Composable
fun BookingRegistrationScreen(
    popBack: () -> Unit,
    tableNumber: String,
    tablePersons: String,
    finishBooking: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.primary),
        content = {
            Toolbar(backButtonListener = popBack)
            PersonalDataContainer(persons = tablePersons)
            ActionsContainer(finishBooking = finishBooking)
        }
    )
}

@Composable
fun Toolbar(backButtonListener: () -> Unit) {

    TopAppBar(
        title = {
            Text(
                text = "Ваши данные",
                fontSize = 16.sp,
                color = MaterialTheme.colors.secondary,
                modifier = Modifier.fillMaxWidth()
            )
        },
        navigationIcon = {
            IconButton(
                onClick = backButtonListener,
                content = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_arrow_left_black),
                        contentDescription = "ArrowBack"
                    )
                }
            )
        },
        modifier = Modifier
            .height(height = 44.dp)
            .fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.primary
    )
}


@Composable
fun PersonalDataContainer(persons: String) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.Center,
        content = {

            InputField(hint = "Как к вам обращаться")

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height = 16.dp)
            )

            InputField(hint = "Телефон")

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height = 16.dp)
            )

            InputField(hint = "Дата и время")

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height = 16.dp)
            )

            TablePersonsInfo(persons = persons)
        }
    )
}


@Composable
fun InputField(hint: String) {

    Column(
        content = {
            val textState = remember { mutableStateOf(TextFieldValue()) }

            TextField(
                value = textState.value,
                textStyle = TextStyle(color = Color.Black, fontSize = 14.sp),
                label = { Text(text = hint, color = Color.DarkGray) },
                onValueChange = { textState.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colors.primary),
                shape = RoundedCornerShape(size = 6.dp)
            )
        }
    )
}

@Composable
fun TablePersonsInfo(persons: String) {

    Row(
        content = {
            Image(
                painter = painterResource(id = R.drawable.ic_alert_circle_gray),
                contentDescription = "ic_alert_circle_gray"
            )

            Text(
                text = "Ваш стол на $persons персон",
                color = Color.DarkGray,
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    )
}

@Composable
fun ActionsContainer(finishBooking: () -> Unit) {

    Column(
        content = {
//            Button(
//                onClick = {
//
//                },
//                modifier = Modifier
//                    .height(height = 46.dp)
//                    .padding(horizontal = 16.dp)
//                    .fillMaxWidth(),
//                border = BorderStroke(
//                    width = 1.dp,
//                    color = Color.Blue,
//                ),
//                shape = RoundedCornerShape(size = 4.dp)
//            ) {
//                Text(
//                    text = "Сделать предзаказ",
//                    fontSize = 16.sp,
//                    color = Color.Blue,
//                    textAlign = TextAlign.Center,
//                    modifier = Modifier
//                        .weight(weight = 1f)
//                        .fillMaxWidth()
//                )
//            }
//
//            Spacer(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(height = 8.dp)
//            )

            Button(
                onClick = finishBooking,
                modifier = Modifier
                    .height(height = 46.dp)
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Yellow
                ),
                shape = RoundedCornerShape(size = 4.dp)
            ) {
                Text(
                    text = "Забронировать стол",
                    fontSize = 16.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(weight = 1f)
                        .fillMaxWidth()
                )
            }
        },
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 8.dp),
        verticalArrangement = Arrangement.Bottom
    )
}
