package woowacourse.payments.ui.cardRegister.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.common.model.Card
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.theme.FF333333
import woowacourse.payments.ui.theme.FFCBBA64
import woowacourse.payments.ui.theme.FFFFFFFF
import woowacourse.payments.ui.theme.Typography

@Composable
fun PaymentCard(
    modifier: Modifier = Modifier,
    card: Card? = null,
) {
    val formattedNumber: String =
        remember(card?.number) {
            formatCardNumber(card?.number ?: "")
        }
    val formattedDate: String =
        remember(card?.expiredDate) {
            formatExpiredDate(card?.expiredDate ?: "")
        }

    Box(
        contentAlignment = if (card == null) Alignment.Center else Alignment.BottomCenter,
        modifier =
            modifier
                .shadow(8.dp)
                .size(width = 208.dp, height = 124.dp)
                .background(
                    color = FF333333,
                    shape = RoundedCornerShape(5.dp),
                )
                .padding(bottom = 16.dp)
                .padding(horizontal = 14.dp),
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier =
                    Modifier
                        .size(width = 40.dp, height = 26.dp)
                        .background(
                            color = FFCBBA64,
                            shape = RoundedCornerShape(4.dp),
                        ),
            )
            if (card != null) {
                Text(
                    text = formattedNumber,
                    color = FFFFFFFF,
                    style = Typography.labelMedium,
                    modifier = Modifier.padding(top = 8.dp),
                )

                Row(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 2.dp),
                ) {
                    Text(
                        text = card.ownerName ?: "",
                        color = FFFFFFFF,
                        style = Typography.labelMedium,
                        textAlign = TextAlign.Start,
                    )

                    Text(
                        text = formattedDate,
                        color = FFFFFFFF,
                        style = Typography.labelMedium,
                        textAlign = TextAlign.End,
                        modifier = Modifier.weight(1f),
                    )
                }
            }
        }
    }
}

private fun formatCardNumber(number: String): String {
    val visibleNumber = number.take(8).chunked(4).joinToString(" - ")
    return "$visibleNumber - **** - ****"
}

private fun formatExpiredDate(date: String): String =
    if (date.length == 4) {
        "${date.take(2)} / ${date.takeLast(2)}"
    } else {
        date
    }

@Preview(showBackground = true)
@Composable
private fun PaymentCardPreview1() {
    AndroidpaymentsTheme {
        PaymentCard(
            card =
                Card(
                    number = "1111222233334444",
                    expiredDate = "0421",
                    ownerName = "CREW",
                    password = "1234",
                ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PaymentCardPreview2() {
    AndroidpaymentsTheme {
        PaymentCard()
    }
}

@Preview(showBackground = true)
@Composable
private fun PaymentCardPreview3() {
    AndroidpaymentsTheme {
        PaymentCard(
            card =
                Card(
                    number = "1111222233334444",
                    expiredDate = "0421",
                    ownerName = null,
                    password = "1234",
                ),
        )
    }
}
