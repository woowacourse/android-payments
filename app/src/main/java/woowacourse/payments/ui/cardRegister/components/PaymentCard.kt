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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.common.model.Card
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.theme.Typography

@Composable
fun PaymentCard(
    modifier: Modifier = Modifier,
    card: Card? = null,
) {
    Box(
        contentAlignment = if (card == null) Alignment.Center else Alignment.BottomCenter,
        modifier =
            modifier
                .shadow(8.dp)
                .size(width = 208.dp, height = 124.dp)
                .background(
                    color = Color(0xFF333333),
                    shape = RoundedCornerShape(5.dp),
                ).padding(bottom = 16.dp)
                .padding(horizontal = 14.dp),
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
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
                    text = card.number,
                    color = Color.White,
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
                        color = Color.White,
                        style = Typography.labelMedium,
                        textAlign = TextAlign.Start,
                    )

                    Text(
                        text = card.expiredDate,
                        color = Color.White,
                        style = Typography.labelMedium,
                        textAlign = TextAlign.End,
                        modifier = Modifier.weight(1f),
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PaymentCardPreview1() {
    AndroidpaymentsTheme {
        PaymentCard(
            card =
                Card(
                    number = "1111 - 2222 - **** - ****",
                    expiredDate = "04 / 21",
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
