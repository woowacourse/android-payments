package woowacourse.payments.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

@Composable
fun PaymentCard(
    modifier: Modifier = Modifier,
    paymentCardUiModel: PaymentCardUiModel? = null,
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier =
            modifier
                .shadow(8.dp)
                .size(width = 208.dp, height = 124.dp)
                .background(
                    color = Color.DarkGray,
                    shape = RoundedCornerShape(5.dp),
                ).padding(16.dp),
    ) {
        Column(
            modifier = Modifier.matchParentSize(),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            PaymentCardText("")
            PaymentCardChip()
            PaymentCardInfo(paymentCardUiModel)
        }
    }
}

@Composable
private fun PaymentCardChip(modifier: Modifier = Modifier) {
    Box(
        modifier =
            modifier
                .size(width = 40.dp, height = 26.dp)
                .background(
                    color = Color(0xFFCBBA64),
                    shape = RoundedCornerShape(4.dp),
                ),
    )
}

@Composable
private fun PaymentCardText(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        color = Color.White,
        fontSize = 12.sp,
        fontWeight = FontWeight.W500,
        letterSpacing = 1.sp,
        lineHeight = 1.em,
        modifier = modifier,
    )
}

@Composable
private fun PaymentCardInfo(
    paymentCardUiModel: PaymentCardUiModel?,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Bottom,
    ) {
        PaymentCardText(paymentCardUiModel?.maskedNumber().orEmpty())

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            PaymentCardText(paymentCardUiModel?.upperCardholderName.orEmpty())
            PaymentCardText(paymentCardUiModel?.formattedExpirationDate().orEmpty())
        }
    }
}

@Preview
@Composable
fun PaymentCardPreview() {
    val paymentCardUiModel =
        PaymentCardUiModel(
            number = CardNumberUiModel("1234567812345678"),
            expirationDate = CardExpirationDateUiModel("1224"),
            cardholderName = CardholderNameUiModel("JOHN DOE"),
        )
    PaymentCard(paymentCardUiModel = paymentCardUiModel)
}
