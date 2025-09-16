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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.domain.BankType
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.util.toCardCompanyUiModel

@Composable
fun PaymentCard(
    card: CardUiModel,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier =
            modifier
                .shadow(8.dp)
                .size(width = 240.dp, height = 140.dp)
                .background(
                    color = card.cardCompanyUiModel.backgroundColor,
                    shape = RoundedCornerShape(5.dp),
                ),
        contentAlignment = Alignment.CenterStart,
    ) {
        Box(
            modifier =
                Modifier
                    .padding(start = 14.dp, bottom = 12.dp)
                    .size(width = 40.dp, height = 26.dp)
                    .background(
                        color = Color(0xFFCBBA64),
                        shape = RoundedCornerShape(4.dp),
                    ),
        )

        Column(
            modifier =
                Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
        ) {
            Text(
                text = card.formattedNumber,
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = card.owner,
                    color = Color.White,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium,
                )

                Text(
                    text = card.formattedExpired,
                    color = Color.White,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium,
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun BCCardPreview() {
    AndroidpaymentsTheme {
        PaymentCard(
            card =
                CardUiModel(
                    cardCompanyUiModel = BankType.WOORI.toCardCompanyUiModel(),
                    number = "1234567887654321",
                    expired = "0826",
                    owner = "으어 글씨가 너무 크다.",
                ),
        )
    }
}

@Composable
@Preview(showBackground = true)
fun WooriCardPreview() {
    AndroidpaymentsTheme {
        PaymentCard(
            card =
                CardUiModel(
                    cardCompanyUiModel = BankType.KAKAOBANK.toCardCompanyUiModel(),
                    number = "1234567887654321",
                    expired = "0826",
                    owner = "으어 글씨가 너무 크다.",
                ),
        )
    }
}
