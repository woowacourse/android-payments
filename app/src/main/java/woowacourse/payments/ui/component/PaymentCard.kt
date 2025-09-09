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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.model.Card
import woowacourse.payments.ui.theme.CardTextStyle

@Composable
fun PaymentCard(
    modifier: Modifier = Modifier,
    card: Card?,
) {
    Box(
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
                    .padding(start = 14.dp, top = 44.dp, end = 14.dp),
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
            if (card != null) {
                Text(
                    text = card.cardNumber.chunked(4).joinToString(" - "),
                    style = CardTextStyle,
                    color = Color.White,
                    modifier = Modifier.padding(top = 8.dp),
                )

                Row(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 2.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = card.owner,
                        style = CardTextStyle,
                        color = Color.White,
                    )
                    Text(
                        text = card.expiry.chunked(2).joinToString(" / "),
                        style = CardTextStyle,
                        color = Color.White,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisteredPaymentCardPreview() {
    PaymentCard(Modifier, Card("1234567812345678", "0511", "minjeong"))
}
