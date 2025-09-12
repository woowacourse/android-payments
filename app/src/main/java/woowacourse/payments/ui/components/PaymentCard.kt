package woowacourse.payments.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun PaymentCard(
    modifier: Modifier = Modifier,
    card: CardUiModel?,
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier =
            modifier
                .shadow(8.dp)
                .size(width = 208.dp, height = 124.dp)
                .background(
                    color = Color(0xFF333333),
                    shape = RoundedCornerShape(5.dp),
                ),
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(14.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.Start,
        ) {
            Box(
                modifier =
                    Modifier
                        .size(width = 40.dp, height = 26.dp)
                        .background(
                            color = Color(0xFFCBBA64),
                            shape = RoundedCornerShape(4.dp),
                        ),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                fontWeight = FontWeight.W500,
                text = card?.cardNumber.toString(),
                color = Color.White,
                lineHeight = 12.sp,
                fontSize = 12.sp,
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom,
            ) {
                Text(
                    lineHeight = 10.sp,
                    text = card?.userName.toString(),
                    color = Color.White,
                    fontWeight = FontWeight.W500,
                    fontSize = 10.sp,
                )

                Spacer(Modifier.height(2.dp))
                Text(
                    lineHeight = 10.sp,
                    text = card?.expirationDate.toString(),
                    color = Color.White,
                    fontWeight = FontWeight.W500,
                    fontSize = 10.sp,
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(8.dp))
}

@Preview(showBackground = true)
@Composable
private fun PaymentCardPreview() {
    AndroidpaymentsTheme {
        val sampleCard =
            CardUiModel(
                cardNumber = "1111222233334444",
                expirationDate = "0421",
                userName = "KIMGAHYUN",
                password = "1234",
            )
        PaymentCard(
            modifier = Modifier,
            card = sampleCard,
        )
    }
}
