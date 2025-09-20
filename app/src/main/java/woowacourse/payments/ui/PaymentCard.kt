package woowacourse.payments.ui

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
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.list.CardUiModel
import woowacourse.payments.newCard.toUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.theme.Gold
import woowacourse.payments.ui.theme.PaymentCardTextStyle

@Composable
fun PaymentCard(
    state: PaymentCardState,
    modifier: Modifier = Modifier,
) {
    when (state) {
        PaymentCardState.Empty -> EmptyPaymentCard(modifier)
        is PaymentCardState.CardInfo -> PaymentCardContent(card = state.card)
    }
}

@Composable
fun PaymentCardContent(
    card: CardUiModel,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier =
            modifier
                .size(208.dp, 124.dp)
                .shadow(8.dp)
                .background(
                    color = card?.company?.toUiModel()?.color
                        ?: CardCompany.NOT_SELECTED.toUiModel().color,
                    shape = RoundedCornerShape(5.dp),
                )
                .padding(horizontal = 16.dp),
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxSize(),
        ) {
            Box(
                modifier = Modifier.height(44.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = card.company.toUiModel().displayName,
                    style = PaymentCardTextStyle,
                )
            }
            Box(
                modifier =
                    modifier
                        .size(40.dp, 28.dp)
                        .background(
                            color = Gold,
                            shape = RoundedCornerShape(5.dp),
                        ),
            )
            Box(modifier = Modifier.height(54.dp)) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = card.number,
                        style = PaymentCardTextStyle,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            text = card.name ?: "",
                            style = PaymentCardTextStyle,
                        )
                        Text(
                            text = card.expiry,
                            style = PaymentCardTextStyle,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun EmptyPaymentCard(
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier =
            modifier
                .size(208.dp, 124.dp)
                .shadow(8.dp)
                .background(
                    color = Color(0xFF333333),
                    shape = RoundedCornerShape(5.dp),
                )
                .padding(horizontal = 16.dp),
    ) {
        Box(
            modifier =
                modifier
                    .size(40.dp, 28.dp)
                    .background(
                        color = Gold,
                        shape = RoundedCornerShape(5.dp),
                    ),
        )
    }
}

@Preview(name = "정보가 있는 카드")
@Composable
private fun PaymentCardPreview() {
    AndroidpaymentsTheme {
        PaymentCard(state = PaymentCardState.CardInfo(CardUiModel("0000 - 0000 - **** - ****", "10 / 25", "1234", "CREW")))
    }
}

@Preview(name = "빈 카드")
@Composable
private fun EmptyPaymentCardPreview() {
    AndroidpaymentsTheme {
        PaymentCard(state = PaymentCardState.Empty)
    }
}
