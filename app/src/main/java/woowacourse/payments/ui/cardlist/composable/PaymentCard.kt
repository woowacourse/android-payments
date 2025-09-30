package woowacourse.payments.ui.cardlist.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.domain.BankType
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardExpirationDate
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.OwnerName
import woowacourse.payments.domain.Password

@Composable
fun PaymentCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Box(
        contentAlignment = Alignment.BottomStart,
        modifier =
            modifier
                .shadow(8.dp)
                .size(width = 208.dp, height = 124.dp)
                .graphicsLayer {
                    this.shape = RoundedCornerShape(5.dp)
                    this.clip = true
                }.background(
                    color = Color.Black,
                ),
    ) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
private fun PaymentCardPreview() {
    PaymentCard(
        content = {
            RegisterPaymentCard(
                card =
                    Card(
                        bank = BankType.BC,
                        number = CardNumber.fromRawInput("1234123412341234"),
                        expirationDate = CardExpirationDate.fromRawInput("12/34"),
                        ownerName = OwnerName.fromRawInput("Yerin"),
                        password = Password.fromRawInput("12"),
                    ).toUiModel(),
            )
        },
    )
}
