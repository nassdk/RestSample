package com.example.rest.feature.menu

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberImagePainter
import com.example.rest.feature.menu.data.Category
import com.example.rest.feature.menu.data.ProductSmall
import com.example.rest.ui.common.CustomDividerView
import com.example.rest.ui.common.ToolbarBase
import kotlinx.coroutines.launch

@Composable
fun MenuScreen(
    backButtonListener: () -> Unit,
    onProductClicked: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.primary),
        content = {
            ToolbarBase(navigationClickListener = backButtonListener, title = "VERANDA")
            Categories()
            CustomDividerView()
            Products(onProductClicked = onProductClicked)
        }
    )
}

@Composable
fun Categories() {

    val state = rememberLazyListState()
    val selectedPosition = remember { mutableStateOf(0) }
    val coroutineScope = rememberCoroutineScope()

    LazyRow(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth()
            .height(height = 34.dp),
        state = state,
        contentPadding = PaddingValues(horizontal = 8.dp),
        content = {
            mockkCategories.forEachIndexed { index, category ->
                item {
                    CategoryItem(
                        model = category,
                        isSelected = selectedPosition.value == index,
                        categorySelected = {
                            selectedPosition.value = index
                            coroutineScope.launch {
                                state.animateScrollToItem(index = index)
                            }
                        }
                    )
                }
            }
        }
    )
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Products(onProductClicked: () -> Unit) {

    val itemWidth = (LocalConfiguration.current.screenWidthDp / 2 - 40).dp

    LazyVerticalGrid(
        cells = GridCells.Fixed(count = 2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
        content = {
            mockkProducts.forEach { product ->
                item {
                    Product(
                        model = product,
                        itemWidth = itemWidth,
                        onProductClicked = onProductClicked
                    )
                }
            }
        }
    )
}

@Composable
fun CategoryItem(model: Category, isSelected: Boolean, categorySelected: () -> Unit) {

    Button(
        onClick = categorySelected,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (isSelected) Color.Blue else MaterialTheme.colors.primary
        ),
        border = BorderStroke(width = if (isSelected) 0.dp else 1.dp, color = Color.Gray),
        modifier = Modifier
            .fillMaxHeight()
            .padding(horizontal = 8.dp),
        content = {
            Image(
                painter = painterResource(id = model.icon),
                contentDescription = "category_icon",
                modifier = Modifier
                    .padding(start = 16.dp)
                    .size(size = 16.dp)
                    .align(alignment = Alignment.CenterVertically)
            )

            Text(
                text = model.title,
                color = if (isSelected) Color.White else Color.Black,
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(start = 8.dp, end = 16.dp)
                    .align(alignment = Alignment.CenterVertically)
            )
        }
    )
}

@Composable
fun Product(model: ProductSmall, itemWidth: Dp, onProductClicked: () -> Unit) {

    val imageSize = itemWidth - 48.dp

    Column(
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 4.dp)
            .size(height = 300.dp, width = itemWidth)
            .background(
                color = MaterialTheme.colors.primary,
                shape = RoundedCornerShape(size = 4.dp)
            )
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(size = 4.dp)
            )
            .clickable(
                onClick = onProductClicked
            ),
        content = {

            ConstraintLayout(
                modifier = Modifier.fillMaxSize(),
                content = {

                    val (image, name, price, purchase) = createRefs()

                    Image(
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .size(size = imageSize)
                            .constrainAs(
                                ref = image,
                                constrainBlock = {
                                    top.linkTo(anchor = parent.top)
                                    start.linkTo(anchor = parent.start)
                                    end.linkTo(anchor = parent.end)
                                }
                            ),
                        painter = rememberImagePainter(data = model.image),
                        contentDescription = "product_image"
                    )

                    Text(
                        text = model.name,
                        color = Color.Black,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(start = 12.dp, end = 12.dp, top = 12.dp)
                            .constrainAs(
                                ref = name,
                                constrainBlock = {
                                    top.linkTo(anchor = image.bottom)
                                    start.linkTo(anchor = parent.start)
                                    end.linkTo(anchor = parent.end)
                                }
                            ),
                    )

                    Text(
                        text = "${model.price} ₽",
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(start = 12.dp, end = 12.dp, top = 12.dp)
                            .constrainAs(
                                ref = price,
                                constrainBlock = {
                                    bottom.linkTo(anchor = purchase.top)
                                    start.linkTo(anchor = parent.start)
                                    end.linkTo(anchor = parent.end)
                                }
                            ),
                    )

                    Button(
                        onClick = { /* TODDO */ },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Yellow
                        ),
                        modifier = Modifier
                            .padding(horizontal = 12.dp, vertical = 16.dp)
                            .height(height = 38.dp)
                            .fillMaxWidth()
                            .constrainAs(
                                ref = purchase,
                                constrainBlock = {
                                    bottom.linkTo(anchor = parent.bottom)
                                    start.linkTo(anchor = parent.start)
                                    end.linkTo(anchor = parent.end)
                                }
                            ),
                        content = {
                            Text(
                                text = "Заказать",
                                textAlign = TextAlign.Center,
                                color = Color.Black,
                                fontSize = 16.sp,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    )
                }
            )
        }
    )
}

val mockkCategories = listOf(
    Category(title = "Новинки"),
    Category(title = "Бургеры"),
    Category(title = "Пиццы"),
    Category(title = "Салаты"),
    Category(title = "Сендвичи"),
    Category(title = "Напитки"),
    Category(title = "Десерты")
)

val mockkProducts = listOf(
    ProductSmall(
        name = "Карбонара в сливочном соусе с креветками",
        image = "https://raw.githubusercontent.com/Aselder-Over/MockApiTestProject/main/ImageMenu/Category/HotDishCategory/Group%2034.png",
        price = 450,
        quantity = 0
    ),
    ProductSmall(
        name = "Том - ям с устрицами и мидиями",
        image = "https://raw.githubusercontent.com/Aselder-Over/MockApiTestProject/main/ImageMenu/Category/HotDishCategory/Group%2034.png",
        price = 400,
        quantity = 2
    ),
    ProductSmall(
        name = "Пицца Санта Барбарро",
        image = "https://raw.githubusercontent.com/Aselder-Over/MockApiTestProject/main/ImageMenu/Category/HotDishCategory/Group%2034.png",
        price = 380,
        quantity = 1
    ),
    ProductSmall(
        name = "Шашлык из куриных крылышек",
        image = "https://raw.githubusercontent.com/Aselder-Over/MockApiTestProject/main/ImageMenu/Category/HotDishCategory/Group%2034.png",
        price = 229,
        quantity = 4
    ),
    ProductSmall(
        name = "Карбонара в сливочном соусе с креветками",
        image = "https://raw.githubusercontent.com/Aselder-Over/MockApiTestProject/main/ImageMenu/Category/HotDishCategory/Group%2034.png",
        price = 450,
        quantity = 0
    ),
    ProductSmall(
        name = "Карбонара в сливочном соусе с креветками",
        image = "https://raw.githubusercontent.com/Aselder-Over/MockApiTestProject/main/ImageMenu/Category/HotDishCategory/Group%2034.png",
        price = 450,
        quantity = 0
    )
)