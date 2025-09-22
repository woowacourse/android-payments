package woowacourse.payments.ui.common.composable

import android.content.Context
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.model.toUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun PaymentCard(
    modifier: Modifier = Modifier,
    card: CardUiModel = CardUiModel.EMPTY,
) {
    val context: Context = LocalContext.current

    Box(
        modifier
            .shadow(8.dp)
            .size(width = 208.dp, height = 124.dp)
            .background(
                color = Color(card.cardCompany.cardColor),
                shape = RoundedCornerShape(5.dp),
            ),
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 15.dp),
        ) {
            Text(
                text = context.getString(card.cardCompany.nameRes),
                color = Color.White,
                fontSize = 12.sp,
                modifier = Modifier.testTag("payment card card company name")
            )

            Box(
                Modifier
                    .size(width = 40.dp, height = 26.dp)
                    .background(
                        color = Color(0xFFCBBA64),
                        shape = RoundedCornerShape(4.dp),
                    ),
            )

            Text(
                text = card.cardNumber,
                color = Color.White,
                fontSize = 12.sp,
                modifier = Modifier.testTag("payment card card number")
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = card.cardholderName,
                    color = Color.White,
                    fontSize = 12.sp,
                    modifier = Modifier.testTag("payment card cardholder name")
                )

                Text(
                    text = card.expirationDate,
                    color = Color.White,
                    fontSize = 12.sp,
                    modifier = Modifier.testTag("payment card expiration date")
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "카드 (내용 없음)")
@Composable
private fun BlankPaymentCardPreview() {
    AndroidpaymentsTheme {
        PaymentCard()
    }
}

@Preview(showBackground = true, name = "카드 (내용 있음)")
@Composable
private fun PaymentCardWithDetailPreview() {
    AndroidpaymentsTheme {
        PaymentCard(
            card =
                CardUiModel(
                    "1234 - 1234 - **** - ****",
                    "34 / 12",
                    "CREW",
                    "1234",
                    CardCompany.BC_CARD.toUiModel(),
                ),
        )
    }
}
