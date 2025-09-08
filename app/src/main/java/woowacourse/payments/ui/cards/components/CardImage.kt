package woowacourse.payments.ui.cards.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.ui.model.CardHolderUiModel
import woowacourse.payments.ui.model.CardNumberUiModel
import woowacourse.payments.ui.model.ExpirationDateUiModel
import woowacourse.payments.ui.model.PaymentCardUiModel

@Composable
fun CardItem(
    paymentCard: PaymentCardUiModel,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier =
            modifier
                .shadow(8.dp)
                .size(width = 208.dp, height = 124.dp)
                .background(
                    color = colorResource(R.color.card_background),
                    shape = RoundedCornerShape(5.dp),
                ),
    ) {
        Column(
            modifier =
                Modifier
                    .padding(horizontal = 14.dp, vertical = 16.dp),
        ) {
            Box(
                modifier =
                    Modifier
                        .size(width = 40.dp, height = 26.dp)
                        .background(
                            color = colorResource(R.color.card_chip_gold),
                            shape = RoundedCornerShape(4.dp),
                        ),
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = formatCardNumber(paymentCard.cardNumber.value),
                color = Color.White,
            )

            Spacer(modifier = Modifier.height(2.dp))

            Row(
                modifier =
                    Modifier
                        .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = paymentCard.cardHolder.value,
                    color = Color.White,
                )
                Text(
                    text = formatExpirationDate(paymentCard.expirationDate.value),
                    color = Color.White,
                )
            }
        }
    }
}

private fun formatCardNumber(cardNumber: String): String {
    val digits = cardNumber.filter { it.isDigit() }.take(16)

    val chunks = digits.chunked(4)

    return chunks
        .mapIndexed { index, chunk ->
            if (index < 2) {
                chunk
            } else {
                "****"
            }
        }.joinToString(" - ")
}

private fun formatExpirationDate(expirationDate: String): String {
    val digits = expirationDate.filter { it.isDigit() }.take(4)

    val chunks = digits.chunked(2)

    return chunks.joinToString(" / ")
}

@Preview(showBackground = true)
@Composable
private fun CardPreview() {
    CardItem(
        PaymentCardUiModel(
            cardNumber = CardNumberUiModel("1234 5678 9012 3456"),
            cardHolder = CardHolderUiModel("홍길동"),
            expirationDate = ExpirationDateUiModel("1225"),
        ),
    )
}
