package com.example.rest.feature.productdetails

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberImagePainter
import com.example.rest.R
import com.example.rest.feature.menu.DividerView
import com.example.rest.feature.menu.data.ProductSmall
import com.example.rest.feature.menu.mockkProducts
import com.example.rest.ui.common.ToolbarBase
import com.google.accompanist.pager.*
import kotlin.math.absoluteValue


@Composable
fun ProductDetailsScreen(
    backButtonListener: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        content = {
            ToolbarBase(
                navigationClickListener = backButtonListener,
                title = "Том - ям с устрицами и мидиями"
            )
            Content()
        }
    )
}

@Composable
fun Content() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
            .verticalScroll(state = rememberScrollState()),
        content = {
            ProductImages()
            ProductName()
            ProductDescription()
            ProductPrice()
            DividerView(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
            )
            Relations()
        }
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProductImages() {

    val screenWidth = LocalConfiguration.current.screenWidthDp
    val imageSize = screenWidth * 0.67
    val itemHeight = imageSize + 30

    val pagerState = rememberPagerState(pageCount = mockkProductImages.size)

    Column(
        modifier = Modifier.fillMaxWidth(),
        content = {
            HorizontalPager(state = pagerState, modifier = Modifier.fillMaxWidth()) { page ->
                Box(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                        .height(height = itemHeight.dp)
                        .graphicsLayer {
                            // Calculate the absolute offset for the current page from the
                            // scroll position. We use the absolute value which allows us to mirror
                            // any effects for both directions
                            val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                            // We animate the scaleX + scaleY, between 85% and 100%
                            lerp(
                                start = 0.85f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            ).also { scale ->
                                scaleX = scale
                                scaleY = scale
                            }

                            // We animate the alpha, between 50% and 100%
                            alpha = lerp(
                                start = 0.5f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            )
                        },
                    content = {
                        Image(
                            modifier = Modifier
                                .size(height = imageSize.dp, width = screenWidth.dp)
                                .align(alignment = Alignment.Center),
                            painter = rememberImagePainter(data = mockkProductImages[page]),
                            contentDescription = "ic_product_details"
                        )
                    }
                )
            }

            HorizontalPagerIndicator(
                pagerState = pagerState,
                activeColor = Color.Blue,
                inactiveColor = Color.LightGray,
                modifier = Modifier
                    .padding(12.dp)
                    .align(Alignment.CenterHorizontally),
            )
        }
    )
}

@Composable
fun ProductName() {

    ConstraintLayout(
        modifier = Modifier
            .padding(end = 16.dp, top = 20.dp)
            .fillMaxWidth()
            .padding(start = 28.dp) //Х*Й ЗНАЕТ КАК РАБОТАЕТ, НО ТОЛЬКО ТАК ОНИ ВЫРАВНИВАЮТССЯ
        ,
        content = {
            val (name, infoButton) = createRefs()

            Text(
                text = "Карбонара в сливочном соусе с креветками",
                fontSize = 18.sp,
                fontStyle = FontStyle.Normal,
                color = Color.Black,
                modifier = Modifier.constrainAs(
                    ref = name, constrainBlock = {
                        top.linkTo(anchor = parent.top)
                        start.linkTo(anchor = parent.start)
                        bottom.linkTo(anchor = parent.bottom)
                        end.linkTo(anchor = infoButton.start)
                    }
                )
            )


            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(size = 24.dp)
                    .constrainAs(
                        ref = infoButton,
                        constrainBlock = {
                            end.linkTo(anchor = parent.end)
                            top.linkTo(anchor = parent.top)
                        }
                    ),
                content = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_info_light_gray),
                        contentDescription = "ic_info_btn"
                    )
                }
            )
        }
    )
}

@Composable
fun ProductDescription() {

    Text(
        text = "Паста яичная, сливки, сыр пармезан, креветки, базилик, тмин, укроп, перец черный",
        fontSize = 14.sp,
        color = Color.LightGray,
        modifier = Modifier
            .padding(top = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .padding(start = 16.dp)
    )
}

@Composable
fun ProductPrice() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
        content = {
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.CenterStart)
                    .border(
                        width = 1.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(size = 4.dp)
                    ),
                content = {
                    Text(
                        text = "450 ₽",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            )

            Button(
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(size = 4.dp),
                modifier = Modifier.align(alignment = Alignment.CenterEnd),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow),
                content = {
                    Text(
                        text = "Добавить в заказ",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
            )
        }
    )
}

@Composable
fun Relations() {

    Column(
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxSize(),
        content = {
            Text(
                text = "Добавить в блюдо",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(start = 16.dp)
            )


            LazyRow(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 12.dp),
                content = {
                    mockkProducts.forEach { additionalProduct ->
                        item {
                            AdditionalProduct(model = additionalProduct)
                        }
                    }
                }
            )
        }
    )
}

@Composable
fun AdditionalProduct(model: ProductSmall) {

    ConstraintLayout(
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .size(height = 224.dp, width = 132.dp)
            .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(size = 4.dp)),
        content = {

            val (image, name, price, toCart) = createRefs()

            Image(
                painter = rememberImagePainter(data = model.image),
                contentDescription = "ic_additional_product",
                modifier = Modifier
                    .padding(top = 12.dp)
                    .size(size = 97.dp)
                    .constrainAs(
                        ref = image,
                        constrainBlock = {
                            top.linkTo(anchor = parent.top)
                            start.linkTo(anchor = parent.start)
                            end.linkTo(anchor = parent.end)
                        }
                    )
            )

            Text(
                text = model.name,
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontSize = 12.sp,
                maxLines = 2,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 12.dp)
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth()
                    .constrainAs(
                        ref = name,
                        constrainBlock = {
                            top.linkTo(anchor = image.bottom)
                            start.linkTo(anchor = parent.start)
                            end.linkTo(anchor = parent.end)
                        }
                    ),
            )

            Box(
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .border(
                        width = 1.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(size = 4.dp)
                    )
                    .constrainAs(
                        ref = price,
                        constrainBlock = {
                            bottom.linkTo(anchor = parent.bottom)
                            start.linkTo(anchor = parent.start)
                            end.linkTo(anchor = toCart.start)
                        }
                    ),
                content = {
                    Text(
                        text = "${model.price}₽",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
            )

            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .size(size = 32.dp)
                    .constrainAs(
                        ref = toCart,
                        constrainBlock = {
                            end.linkTo(anchor = parent.end)
                            bottom.linkTo(anchor = price.bottom)
                            top.linkTo(anchor = price.top)
                            start.linkTo(anchor = price.end)
                        }
                    ),
                content = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_plus_white),
                        contentDescription = "ic_plus_white",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            )
        }
    )
}

val mockkProductImages = listOf(
    "https://raw.githubusercontent.com/Aselder-Over/MockApiTestProject/main/ImageMenu/Category/HotDishCategory/Group%2034.png",
    "https://raw.githubusercontent.com/Aselder-Over/MockApiTestProject/main/ImageMenu/Category/HotDishCategory/Group%2034.png",
    "https://raw.githubusercontent.com/Aselder-Over/MockApiTestProject/main/ImageMenu/Category/HotDishCategory/Group%2034.png"
)