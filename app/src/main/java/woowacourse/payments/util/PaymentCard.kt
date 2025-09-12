package woowacourse.payments.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.ExpiredDate
import woowacourse.payments.domain.OwnerName
import woowacourse.payments.domain.Password

@Composable
fun PaymentCard(
    modifier: Modifier = Modifier,
    card: Card? = null,
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
        Box(
            modifier =
                Modifier
                    .padding(start = 14.dp, bottom = 10.dp)
                    .size(width = 40.dp, height = 26.dp)
                    .background(
                        color = Color(0xFFCBBA64),
                        shape = RoundedCornerShape(4.dp),
                    ),
        )

        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier =
                modifier
                    .fillMaxHeight()
                    .padding(bottom = 16.dp),
        ) {
            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp),
            ) {
                if (card != null) {
                    Text(
                        text = card.cardNumber.toMaskCardNumber(),
                        style = cardTextStyle,
                        letterSpacing = 2.sp,
                        overflow = TextOverflow.Clip,
                        maxLines = 1,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(
                            text = card.ownerName.name,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            style = cardTextStyle,
                            modifier = Modifier.width(100.dp),
                        )
                        Text(
                            text = card.expiredDate.toFormattedString(),
                            style = cardTextStyle,
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PaymentCardPreview() {
    PaymentCard(
        card =
            Card(
                cardNumber = CardNumber("1234567812345678"),
                expiredDate = ExpiredDate.of(1, 26)!!,
                ownerName = OwnerName("크림"),
                password = Password("1234"),
            ),
    )
}

private fun CardNumber.toMaskCardNumber(): String {
    val maskCardNumber = this.numbers.take(8) + "*".repeat(8)
    return maskCardNumber.chunked(4).joinToString("-")
}

private fun ExpiredDate.toFormattedString(): String {
    val mm: String = if (month < 10) "0$month" else "$month"
    val yy: String = if (year < 10) "0$year" else "$year"
    return "$mm / $yy"
}

private val cardTextStyle =
    TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        letterSpacing = 1.sp,
    )
