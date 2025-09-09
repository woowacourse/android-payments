package woowacourse.payments.ui.newcard

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.ui.formatter.CardNumberFormat
import woowacourse.payments.ui.formatter.ExpirationDateFormat
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import java.time.YearMonth

@Suppress("ktlint:standard:function-naming")
@Composable
fun PaymentCard(
    modifier: Modifier = Modifier,
    cardNumber: String? = null,
    expirationDate: YearMonth? = null,
    cardholderName: String? = null,
) {
    Box(
        modifier
            .shadow(8.dp)
            .size(width = 208.dp, height = 124.dp)
            .background(
                color = Color(0xFF333333),
                shape = RoundedCornerShape(5.dp),
            ),
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 44.dp),
        ) {
            Box(
                Modifier
                    .size(width = 40.dp, height = 26.dp)
                    .background(
                        color = Color(0xFFCBBA64),
                        shape = RoundedCornerShape(4.dp),
                    ),
            )

            Text(
                text =
                    cardNumber?.let { cardNumber: String ->
                        CardNumberFormat.formattedCardNumber(text = cardNumber, applyMask = true)
                    } ?: "",
                color = Color.White,
                fontSize = 12.sp,
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = cardholderName ?: "",
                    color = Color.White,
                    fontSize = 12.sp,
                )

                Text(
                    text =
                        expirationDate?.let { expirationDate: YearMonth ->
                            ExpirationDateFormat.formattedExpirationDate(expirationDate)
                        } ?: "",
                    color = Color.White,
                    fontSize = 12.sp,
                    textAlign = TextAlign.End,
                )
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true)
@Composable
fun BlankPaymentCardPreview() {
    AndroidpaymentsTheme {
        PaymentCard()
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true)
@Composable
fun PaymentCardWithDetailPreview() {
    AndroidpaymentsTheme {
        PaymentCard(
            cardNumber = "1234123412341234",
            cardholderName = "CREW",
            expirationDate = YearMonth.of(2034, 12),
        )
    }
}
