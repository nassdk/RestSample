package com.example.rest.feature.booking.bookingregistration

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rest.R

@Composable
fun BookingRegistrationScreen(
    popBack: () -> Unit,
    tableNumber: String,
    tablePersons: Int
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        content = {
            Toolbar()
            PersonalDataContainer(persons = tablePersons)
            ActionsContainer()
        }
    )
}

@Preview
@Composable
fun Toolbar() {

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
                onClick = { /*TODO*/ },
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
fun PersonalDataContainer(persons: Int) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
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
fun TablePersonsInfo(persons: Int) {

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

@Preview
@Composable
fun ActionsContainer() {

    Column(
        content = {

            Button(
                onClick = {

                },
                modifier = Modifier
                    .background(color = MaterialTheme.colors.primary)
                    .height(height = 46.dp)
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                border = BorderStroke(
                    width = 1.dp,
                    color = Color.Blue,
                ),
                shape = RoundedCornerShape(size = 4.dp)
            ) {

                Text(
                    text = "Сделать предзаказ",
                    fontSize = 16.sp,
                    color = Color.Blue,
                    modifier = Modifier
                        .weight(weight = 1f)
                        .padding(start = 8.dp)
                )
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height = 8.dp)
            )

            Button(
                onClick = {

                },
                modifier = Modifier
                    .background(color = Color.Yellow)
                    .height(height = 46.dp)
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(size = 4.dp)
            ) {

                Text(
                    text = "Забронировать стол",
                    fontSize = 16.sp,
                    color = Color.White,
                    modifier = Modifier
                        .weight(weight = 1f)
                        .padding(start = 8.dp)
                )
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}
