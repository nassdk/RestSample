package com.example.rest.feature.booking.tables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.rest.R
import com.google.accompanist.coil.rememberCoilPainter

@Composable
fun TablesScreen(
    popBack: () -> Unit,
    selectTable: (personCount: Int, tableNumber: String) -> Unit
) {

    Column(
        content = {
            Toolbar(
                navigationClickListener = { popBack.invoke() }
            )
            TablesList(
                tables = listOf(
                    TableModel(
                        id = 1,
                        image = "https://raw.githubusercontent.com/Aselder-Over/MockApiTestProject/main/TableInfo/TablesImages/table11.png",
                        number = "3",
                        personCount = 2
                    ),

                    TableModel(
                        id = 1,
                        image = "https://raw.githubusercontent.com/Aselder-Over/MockApiTestProject/main/TableInfo/TablesImages/table11.png",
                        number = "3",
                        personCount = 2
                    ),

                    TableModel(
                        id = 1,
                        image = "https://raw.githubusercontent.com/Aselder-Over/MockApiTestProject/main/TableInfo/TablesImages/table11.png",
                        number = "3",
                        personCount = 2
                    )
                ),
                selectTable = { persons, table -> selectTable.invoke(persons, table) }
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.primary)
    )
}

@Composable
private fun Toolbar(
    navigationClickListener: () -> Unit
) {

    TopAppBar(
        title = {
            Text(
                text = "Бронирование стола",
                fontSize = 16.sp,
                color = MaterialTheme.colors.secondary,
                modifier = Modifier.fillMaxWidth()
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { navigationClickListener.invoke() },
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
private fun LoadingView() {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize(),
        content = {
            CircularProgressIndicator(
                progress = 0.5f,
                color = MaterialTheme.colors.primary
            )
        }
    )
}

@Composable
private fun TablesList(
    tables: List<TableModel>,
    selectTable: (personCount: Int, tableNumber: String) -> Unit
) {

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(space = 12.dp),
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 12.dp
        ),
        content = {
            tables.forEach {
                item {
                    TableView(
                        table = it,
                        selectTable = { persons, table -> selectTable.invoke(persons, table) }
                    )
                }
            }
        }
    )
}

@Composable
private fun TableView(
    table: TableModel,
    selectTable: (personCount: Int, tableNumber: String) -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 230.dp),
        shape = RoundedCornerShape(size = 6.dp),
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                content = {
                    Image(
                        painter = rememberCoilPainter(request = table.image),
                        contentDescription = "TableImage",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(height = 180.dp)
                    )
                    Button(
                        onClick = { selectTable.invoke(table.personCount, table.number) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(height = 50.dp)
                            .background(color = MaterialTheme.colors.primary),
                        border = BorderStroke(
                            width = 1.dp,
                            color = MaterialTheme.colors.primaryVariant,
                        ),
                        content = {

                            ConstraintLayout(
                                modifier = Modifier
                                    .fillMaxSize(),
                                content = {

                                    val (personCount, divider, tableNumber, bookTitle, chevronRight) = createRefs()

                                    Text(
                                        text = "${table.personCount} персон",
                                        fontSize = 14.sp,
                                        textAlign = TextAlign.Start,
                                        color = MaterialTheme.colors.secondary,
                                        fontStyle = FontStyle.Normal,
                                        fontWeight = FontWeight.SemiBold,
                                        modifier = Modifier.constrainAs(
                                            ref = personCount,
                                            constrainBlock = {
                                                top.linkTo(anchor = parent.top)
                                                bottom.linkTo(anchor = parent.bottom)
                                                start.linkTo(anchor = parent.start)
                                            })
                                    )

                                    Divider(
                                        color = Color.Gray,
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .width(width = 1.dp)
                                            .constrainAs(
                                                ref = divider,
                                                constrainBlock = {
                                                    start.linkTo(
                                                        anchor = personCount.end,
                                                        margin = 12.dp
                                                    )
                                                    top.linkTo(anchor = parent.top)
                                                    bottom.linkTo(anchor = parent.bottom)
                                                }
                                            )
                                    )

                                    Text(
                                        text = "Стол №${table.number}",
                                        fontSize = 14.sp,
                                        textAlign = TextAlign.Start,
                                        color = MaterialTheme.colors.secondary,
                                        fontStyle = FontStyle.Normal,
                                        fontWeight = FontWeight.SemiBold,
                                        modifier = Modifier.constrainAs(
                                            ref = tableNumber,
                                            constrainBlock = {
                                                top.linkTo(anchor = parent.top)
                                                bottom.linkTo(anchor = parent.bottom)
                                                start.linkTo(
                                                    anchor = divider.end,
                                                    margin = 12.dp
                                                )
                                            }
                                        )
                                    )

                                    Text(
                                        text = "Забронировать",
                                        fontSize = 14.sp,
                                        color = Color.Blue,
                                        fontStyle = FontStyle.Normal,
                                        fontWeight = FontWeight.SemiBold,
                                        modifier = Modifier.constrainAs(
                                            ref = bookTitle,
                                            constrainBlock = {
                                                top.linkTo(anchor = parent.top)
                                                bottom.linkTo(anchor = parent.bottom)
                                                end.linkTo(
                                                    anchor = chevronRight.start,
                                                    margin = 4.dp
                                                )
                                            }
                                        )
                                    )

                                    Image(
                                        painter = painterResource(id = R.drawable.ic_chevron_right_blue),
                                        contentDescription = "ChevronRightBlue",
                                        modifier = Modifier.constrainAs(
                                            ref = chevronRight,
                                            constrainBlock = {
                                                top.linkTo(anchor = parent.top)
                                                bottom.linkTo(anchor = parent.bottom)
                                                end.linkTo(anchor = parent.end)
                                            }
                                        )
                                    )
                                }
                            )
                        }
                    )
                }
            )
        }
    )

}