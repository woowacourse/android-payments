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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardDigit
import woowacourse.payments.domain.CardExpirationDate
import woowacourse.payments.domain.CardHolderName
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.CardPassword
import java.time.YearMonth

@Composable
fun PaymentCard(
    modifier: Modifier = Modifier,
    card: Card? = null,
) {
    Box(
        contentAlignment = Alignment.BottomStart,
        modifier =
            modifier
                .shadow(8.dp)
                .size(width = 208.dp, height = 124.dp)
                .background(
                    color = Color(0xFF333333),
                    shape = RoundedCornerShape(5.dp),
                ).padding(horizontal = 14.dp, vertical = 16.dp),
    ) {
        Column {
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
                text = card?.number?.toMaskedString() ?: "",
                fontSize = 12.sp,
                letterSpacing = 2.sp,
                color = Color.White,
            )
            Spacer(modifier = Modifier.height(2.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = card?.holderName?.name ?: "",
                    fontSize = 12.sp,
                    letterSpacing = 2.sp,
                    color = Color.White,
                )
                Text(
                    text = card?.expirationDate?.toDisplayString() ?: "",
                    fontSize = 12.sp,
                    letterSpacing = 2.sp,
                    color = Color.White,
                )
            }
        }
    }
}

private fun CardNumber.toMaskedString(): String =
    numbers
        .map { it.value }
        .joinToString("")
        .chunked(4)
        .mapIndexed { index, chunk -> if (index < 2) chunk else "****" }
        .joinToString(" - ")

private fun CardExpirationDate.toDisplayString(): String {
    val month: String = date.monthValue.toString().padStart(2, '0')
    val year: String = (date.year % 100).toString().padStart(2, '0')
    return "$month / $year"
}

@Preview(name = "카드 정보 없음")
@Composable
private fun PaymentCardPreview1() {
    PaymentCard()
}

@Preview(name = "카드 정보 있음")
@Composable
private fun PaymentCardPreview2() {
    PaymentCard(
        card =
            Card(
                number =
                    CardNumber(
                        listOf(1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4)
                            .map(::CardDigit),
                    ),
                expirationDate = CardExpirationDate(YearMonth.of(2025, 9)),
                password = CardPassword("0000"),
                holderName = CardHolderName("CREW"),
            ),
    )
}
