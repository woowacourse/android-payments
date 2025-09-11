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
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.ui.model.CardExpirationDateUiModel
import woowacourse.payments.ui.model.CardNumberUiModel
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.model.CardholderNameUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

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
                    color = Color.DarkGray,
                    shape = RoundedCornerShape(5.dp),
                ),
    ) {
        Column(
            modifier =
                if (card != null) {
                    Modifier.align(Alignment.BottomCenter)
                } else {
                    Modifier.align(Alignment.CenterStart)
                },
        ) {
            Box(
                modifier =
                    Modifier
                        .padding(start = 14.dp)
                        .size(width = 40.dp, height = 26.dp)
                        .align(Alignment.Start)
                        .background(
                            color = Color(0xFFCBBA64),
                            shape = RoundedCornerShape(4.dp),
                        ),
            )

            if (card != null) {
                Column(
                    modifier =
                        Modifier
                            .semantics { contentDescription = "카드 정보" }
                            .padding(start = 14.dp, end = 14.dp),
                ) {
                    Text(
                        text = card.formattedCardNumber(),
                        color = Color.White,
                        fontWeight = FontWeight.W500,
                        fontSize = 12.sp,
                        letterSpacing = 2.0.sp,
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(top = 4.dp),
                        maxLines = 1,
                    )

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.padding(bottom = 5.dp),
                    ) {
                        Text(
                            text = card.cardholderName,
                            modifier = Modifier.weight(1f),
                            color = Color.White,
                            fontWeight = FontWeight.W500,
                            fontSize = 12.sp,
                            letterSpacing = 1.sp,
                            maxLines = 1,
                        )
                        Text(
                            text = card.formattedCardExpirationDate(),
                            color = Color.White,
                            fontWeight = FontWeight.W500,
                            fontSize = 12.sp,
                            letterSpacing = 1.sp,
                            maxLines = 1,
                            textAlign = TextAlign.End,
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "카드 정보 있다면 화면에 보여준다.")
@Composable
private fun HasCardPreview() {
    AndroidpaymentsTheme {
        PaymentCard(
            card =
                CardUiModel(
                    cardholderNameUiModel = CardholderNameUiModel("CREW"),
                    cardNumberUiModel = CardNumberUiModel("1111222233334444"),
                    cardExpirationDateUiModel = CardExpirationDateUiModel("0421"),
                ),
        )
    }
}

@Preview(showBackground = true, name = "카드 정보 없다면 IC Chip만 보인다.")
@Composable
private fun HasNotCardPreview() {
    AndroidpaymentsTheme {
        PaymentCard(card = null)
    }
}
