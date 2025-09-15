package woowacourse.payments.ui.common.components

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
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.designsystem.theme.AndroidpaymentsTheme
import woowacourse.payments.designsystem.theme.GrayBackground
import woowacourse.payments.designsystem.theme.Yellow
import woowacourse.payments.ui.common.model.CardUiModel
import woowacourse.payments.ui.newcard.components.CARD_NUMBER_SEPARATOR

private const val MASKED_DIGITS = "****"

@Composable
fun PaymentCard(
    modifier: Modifier = Modifier,
    card: CardUiModel? = null,
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier =
            modifier
                .shadow(8.dp)
                .size(width = 208.dp, height = 124.dp)
                .background(
                    color = GrayBackground,
                    shape = RoundedCornerShape(5.dp),
                ),
    ) {
        Box(
            modifier =
                Modifier
                    .padding(start = 14.dp, bottom = 10.dp)
                    .size(width = 40.dp, height = 26.dp)
                    .background(
                        color = Yellow,
                        shape = RoundedCornerShape(4.dp),
                    ),
        )

        if (card != null) {
            CardContents(card = card, modifier = Modifier.align(Alignment.BottomStart))
        }
    }
}

@Composable
private fun CardContents(
    card: CardUiModel,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .padding(horizontal = 14.dp)
                .padding(bottom = 14.dp)
                .fillMaxWidth(),
    ) {
        Text(
            text = formatCardNumber(card.numberDigits),
            style =
                TextStyle(
                    color = White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W500,
                ),
        )

        Spacer(Modifier.height(2.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = card.holder,
                style =
                    TextStyle(
                        color = White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W500,
                    ),
            )
            Text(
                text = formatExpiry(card.expiry),
                style =
                    TextStyle(
                        color = White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W500,
                    ),
            )
        }
    }
}

private fun formatCardNumber(digits: String): String {
    val chunks = digits.chunked(4)
    return buildString {
        chunks.forEachIndexed { index, chunk ->
            append(
                when (index) {
                    0, 1 -> chunk
                    else -> MASKED_DIGITS
                },
            )
            if (index < chunks.lastIndex) append(CARD_NUMBER_SEPARATOR)
        }
    }
}

private fun formatExpiry(expiry: String): String = expiry.substring(0, 2) + " / " + expiry.substring(2)

@Preview(showBackground = true)
@Composable
private fun PaymentCardPreview() {
    AndroidpaymentsTheme {
        PaymentCard(card = CardUiModel("1234123412341234", "1225", "공백"))
    }
}
