package com.example.rest.feature.order

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.rememberImagePainter
import com.example.rest.R
import com.example.rest.feature.menu.data.ProductSmall
import com.example.rest.feature.menu.mockkProducts
import com.example.rest.feature.order.data.PaymentType
import com.example.rest.feature.order.data.ui.AdditionalProductsUi
import com.example.rest.feature.order.data.ui.OrderProductsUi
import com.example.rest.feature.order.data.ui.PaymentTypesUi
import com.example.rest.feature.order.data.ui.SummaryUi
import com.example.rest.ui.common.CustomDividerView
import com.example.rest.ui.common.ToolbarBase
import com.example.rest.ui.common.model.DividerUi
import com.example.rest.ui.common.model.HeaderUi
import com.example.rest.ui.common.model.SpaceUi
import com.example.rest.ui.common.ui.HeaderBold16

@Composable
fun OrderScreen(
    backButtonListener: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White),
        content = {
            ToolbarBase(navigationClickListener = backButtonListener, title = "Ваш заказ")
            Content(
                modifier = Modifier
                    .weight(weight = 1f)
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            )
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
                    .height(height = 46.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
                content = {
                    Text(text = "Заказать", fontSize = 16.sp, color = Color.White)
                }
            )
        }
    )
}

@Composable
fun Content(modifier: Modifier = Modifier) {

    val content = arrayListOf<Any>()

    content.run {
        add(OrderProductsUi(products = mockkProducts.take(3)))
        add(DividerUi())
        add(SpaceUi(value = 24.dp))
        add(HeaderUi(title = "Рекомендуем к заказу"))
        add(SpaceUi(value = 16.dp))
        add(AdditionalProductsUi(products = mockkProducts))
        add(SpaceUi(value = 24.dp))
        add(DividerUi())
        add(SpaceUi(value = 24.dp))
        add(HeaderUi(title = "Выберите способ оплаты"))
        add(SpaceUi(value = 8.dp))
        add(PaymentTypesUi(payments = mockkPaymentTypes))
        add(SpaceUi(value = 24.dp))
        add(DividerUi())
        add(SummaryUi())
    }

    LazyColumn(
        modifier = modifier,
        content = {

            content.forEach { model ->
                when (model) {
                    is OrderProductsUi -> {
                        model.products.forEachIndexed { index, product ->
                            item {
                                ProductOrder(model = product)

                                if (index < 2) {
                                    CustomDividerView(
                                        modifier = Modifier
                                            .padding(horizontal = 16.dp)
                                            .fillMaxWidth()
                                    )
                                }
                            }
                        }
                    }

                    is AdditionalProductsUi -> item {
                        AdditionalProducts(products = model.products)
                    }

                    is PaymentTypesUi -> item {
                        PaymentTypesRadioGroup(paymentTypes = model.payments)
                    }

                    is HeaderUi -> item {
                        HeaderBold16(
                            title = model.title, modifier = Modifier.padding(start = 16.dp)
                        )
                    }

                    is SpaceUi -> item {
                        Spacer(modifier = Modifier.padding(top = model.value))
                    }

                    is SummaryUi -> item {
                        SummaryBlock()
                    }

                    is DividerUi -> item {
                        CustomDividerView(modifier = Modifier.fillMaxWidth())
                    }
                }
            }
        }
    )
}

@Composable
fun ProductOrder(model: ProductSmall) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp, horizontal = 16.dp),
        content = {

            val (image, name, price, delete, minus, plus, quantity) = createRefs()

            Image(
                painter = rememberImagePainter(data = model.image),
                contentDescription = "ic_product_order",
                modifier = Modifier
                    .size(size = 72.dp)
                    .constrainAs(
                        ref = image,
                        constrainBlock = {
                            start.linkTo(anchor = parent.start)
                            top.linkTo(anchor = parent.top)
                            bottom.linkTo(anchor = parent.bottom)
                        }
                    )
            )

            Text(
                text = model.name,
                fontSize = 14.sp,
                color = Color.Black,
                maxLines = 2,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .constrainAs(
                        ref = name,
                        constrainBlock = {
                            start.linkTo(anchor = image.end)
                            top.linkTo(anchor = parent.top)
                            end.linkTo(anchor = delete.start)
                            width = Dimension.fillToConstraints
                        }
                    )
            )

            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(size = 16.dp)
                    .constrainAs(
                        ref = delete,
                        constrainBlock = {
                            end.linkTo(anchor = parent.end)
                            top.linkTo(anchor = parent.top)
                        }
                    ),
                content = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_close_gray),
                        contentDescription = "ic_delete_product"
                    )
                }
            )

            Text(
                text = "${model.price}₽",
                fontSize = 14.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .constrainAs(
                        ref = price,
                        constrainBlock = {
                            start.linkTo(anchor = image.end)
                            bottom.linkTo(anchor = minus.bottom)
                            top.linkTo(anchor = minus.top)
                        }
                    )
            )


            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(size = 32.dp)
                    .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(4.dp))
                    .constrainAs(
                        ref = plus,
                        constrainBlock = {
                            end.linkTo(anchor = parent.end)
                            bottom.linkTo(anchor = parent.bottom)
                        }
                    ),
                content = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_plus_white),
                        contentDescription = "ic_plus"
                    )
                }
            )

            Text(
                text = "${model.quantity}",
                fontSize = 14.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .constrainAs(
                        ref = quantity,
                        constrainBlock = {
                            end.linkTo(anchor = plus.start)
                            bottom.linkTo(anchor = plus.bottom)
                            top.linkTo(anchor = plus.top)
                        }
                    )
            )

            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .size(size = 32.dp)
                    .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(4.dp))
                    .constrainAs(
                        ref = minus,
                        constrainBlock = {
                            end.linkTo(anchor = quantity.start)
                            bottom.linkTo(anchor = parent.bottom)
                        }
                    ),
                content = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_minus_black),
                        contentDescription = "ic_minus"
                    )
                }
            )

        }
    )
}

@Composable
fun AdditionalProducts(products: List<ProductSmall>) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        content = {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 12.dp),
                content = {
                    products.forEach { additionalProduct ->
                        item {
                            AdditionalProductView(model = additionalProduct)
                        }
                    }
                }
            )
        }
    )
}

@Composable
fun AdditionalProductView(model: ProductSmall) {

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
                        fontWeight = FontWeight.Bold,
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
                        painter = painterResource(id = R.drawable.ic_plus_white_full),
                        contentDescription = "ic_plus_white",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            )
        }
    )
}

@Composable
fun PaymentTypesRadioGroup(paymentTypes: List<PaymentType>) {

    val (selectedOption, onOptionSelected) = remember { mutableStateOf(paymentTypes.first()) }

    Column {
        paymentTypes.forEach { item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(size = 4.dp)
                    )
                    .selectable(
                        selected = (item == selectedOption),
                        onClick = {
                            onOptionSelected(item)
                        }
                    )
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                content = {
                    RadioButton(
                        colors = RadioButtonDefaults.colors(
                            unselectedColor = Color.LightGray,
                            selectedColor = Color.Blue
                        ),
                        selected = (item == selectedOption),
                        onClick = { onOptionSelected(item) }
                    )

                    Text(
                        text = item.name,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(start = 12.dp)
                            .weight(weight = 1f)
                    )

                    Image(
                        painter = painterResource(id = item.icon),
                        contentDescription = "ic_payment_type_icon"
                    )
                }
            )
        }
    }
}

@Composable
fun SummaryBlock() {

    Box(
        modifier = Modifier
            .padding(top = 24.dp)
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        content = {

            Text(
                text = "3 позиции",
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.align(alignment = Alignment.CenterStart)

            )


            Text(
                text = "1 640 ₽",
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.align(alignment = Alignment.CenterEnd)
            )
        }
    )
}

val mockkPaymentTypes = listOf(
    PaymentType(
        name = "Наличные",
        icon = R.drawable.ic_payment_cash
    ),
    PaymentType(
        name = "Оплата картой",
        icon = R.drawable.ic_bank_card_payment
    )
)

