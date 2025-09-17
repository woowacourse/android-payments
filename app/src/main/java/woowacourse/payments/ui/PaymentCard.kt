package woowacourse.payments.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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

@Composable
fun PaymentCard(
    modifier: Modifier = Modifier,
    card: CardUiModel? = null,
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier =
            modifier
                .size(208.dp, 124.dp)
                .shadow(8.dp)
                .background(
                    color = card?.company?.toUiModel()?.color ?: CardCompany.NOT_SELECTED.toUiModel().color,
                    shape = RoundedCornerShape(5.dp),
                )
                .padding(horizontal = 16.dp),
    ) {
        if (card != null) {
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
                        fontSize = 12.sp,
                        color = Color.White,
                    )
                }
                Box(
                    modifier =
                        modifier
                            .size(40.dp, 28.dp)
                            .background(
                                color = Color(0xFFCBBA64),
                                shape = RoundedCornerShape(5.dp),
                            ),
                )
                Box(modifier = Modifier.height(54.dp)) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(
                            text = card.number,
                            color = Color.White,
                            fontSize = 12.sp,
                            letterSpacing = 2.sp,
                            modifier = Modifier.fillMaxWidth(),
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Text(
                                text = card.name ?: "",
                                color = Color.White,
                                fontSize = 12.sp,
                                lineHeight = 12.sp,
                                letterSpacing = 2.sp,
                            )
                            Text(
                                text = card.expiry,
                                color = Color.White,
                                fontSize = 12.sp,
                                lineHeight = 12.sp,
                                letterSpacing = 1.sp,
                            )
                        }
                    }
                }
            }
        } else {
            Box(
                modifier =
                    modifier
                        .size(40.dp, 28.dp)
                        .background(
                            color = Color(0xFFCBBA64),
                            shape = RoundedCornerShape(5.dp),
                        ),
            )
        }
    }
}

@Preview(name = "정보가 있는 카드")
@Composable
private fun PaymentCardPreview() {
    AndroidpaymentsTheme {
        PaymentCard(card = CardUiModel("0000 - 0000 - **** - ****", "1025", "1234", "CREW"))
    }
}

@Preview(name = "빈 카드")
@Composable
private fun EmptyPaymentCardPreview() {
    AndroidpaymentsTheme {
        PaymentCard()
    }
}
