package com.example.rest.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.rest.R

@Composable
fun BasketView() {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 50.dp)
            .background(color = Color.Blue, shape = RoundedCornerShape(size = 4.dp)),
        content = {
            val (title, price, chevron) = createRefs()

            Text(
                text = "Перейти к заказу",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .constrainAs(
                        ref = title,
                        constrainBlock = {
                            start.linkTo(anchor = parent.start)
                            top.linkTo(anchor = parent.top)
                            bottom.linkTo(anchor = parent.bottom)
                        }
                    )
            )

            Text(
                text = "1 270 ₽",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(end = 4.dp)
                    .constrainAs(
                        ref = price,
                        constrainBlock = {
                            end.linkTo(anchor = chevron.start)
                            top.linkTo(anchor = parent.top)
                            bottom.linkTo(anchor = parent.bottom)
                        }
                    )
            )

            Image(
                painter = painterResource(id = R.drawable.ic_chevron_right_white),
                contentDescription = "ic_chevron_right_white",
                modifier = Modifier
                    .padding(end = 16.dp)
                    .constrainAs(
                        ref = chevron,
                        constrainBlock = {
                            end.linkTo(anchor = parent.end)
                            top.linkTo(anchor = parent.top)
                            bottom.linkTo(anchor = parent.bottom)
                        }
                    )
            )
        }
    )
}