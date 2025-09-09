package woowacourse.payments.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.ExpireDate
import woowacourse.payments.domain.OwnerName
import woowacourse.payments.domain.Password
import woowacourse.payments.domain.PaymentCard
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@Composable
fun PaymentCard(
    modifier: Modifier = Modifier,
    paymentCard: PaymentCard? = null,
) {
    val halfCardNumber = paymentCard?.cardNumber?.value?.takeLast(8)
    val yearMonthFormatter = DateTimeFormatter.ofPattern("MM / yy")

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
                    .padding(start = 16.dp, bottom = 10.dp)
                    .size(width = 40.dp, height = 26.dp)
                    .background(
                        color = Color(0xFFCBBA64),
                        shape = RoundedCornerShape(4.dp),
                    ),
        )
        if (paymentCard != null) {
            Text(
                "${halfCardNumber?.take(4)} - ${halfCardNumber?.takeLast(4)} - **** - ****",
                modifier =
                    Modifier
                        .padding(start = 13.dp, bottom = 28.dp)
                        .align(Alignment.BottomStart),
                fontSize = 12.sp,
                letterSpacing = 0.17.em,
                color = Color.White,
            )

            Text(
                paymentCard.ownerName.value ?: "",
                modifier =
                    Modifier
                        .padding(start = 13.dp, bottom = 10.dp)
                        .align(Alignment.BottomStart),
                fontSize = 12.sp,
                letterSpacing = 0.1.em,
                color = Color.White,
            )

            Text(
                paymentCard.expireDate.value.format(yearMonthFormatter),
                modifier =
                    Modifier
                        .padding(end = 14.dp, bottom = 10.dp)
                        .align(Alignment.BottomEnd),
                fontSize = 12.sp,
                letterSpacing = 0.1.em,
                color = Color.White,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PaymentCardPreview() {
    AndroidpaymentsTheme {
        PaymentCard()
    }
}

@Preview(showBackground = true)
@Composable
fun PaymentCardDetailPreview() {
    AndroidpaymentsTheme {
        PaymentCard(
            paymentCard =
                PaymentCard(
                    CardNumber("1234123412341234"),
                    ExpireDate(YearMonth.now().plusMonths(1)),
                    OwnerName("CREW"),
                    Password("1234"),
                ),
        )
    }
}
