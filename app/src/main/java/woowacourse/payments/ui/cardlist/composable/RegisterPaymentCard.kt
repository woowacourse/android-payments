package woowacourse.payments.ui.cardlist.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.domain.BankType
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardExpirationDate
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.OwnerName
import woowacourse.payments.domain.Password
import woowacourse.payments.ui.model.CardUiModel

@Composable
fun RegisterPaymentCard(
    card: CardUiModel,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .fillMaxSize()
                .background(Color(card.bank.color))
                .padding(horizontal = 16.dp),
    ) {
        Text(
            modifier = Modifier.padding(top = 14.dp),
            text = stringResource(id = card.bank.bankName),
            color = Color.White,
            fontSize = 12.sp,
        )
        Box(
            modifier =
                Modifier
                    .padding(top = 5.dp)
                    .size(width = 40.dp, height = 26.dp)
                    .background(
                        color = Color(0xFFCBBA64),
                        shape = RoundedCornerShape(4.dp),
                    ),
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = card.number.toMaskedString(),
            color = Color.White,
            fontSize = 12.sp,
        )
        Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(card.ownerName.name, color = Color.White, fontSize = 12.sp)
            Text(
                text = card.expirationDate.toFormattedString("/"),
                color = Color.White,
                fontSize = 12.sp,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RegisterPaymentCardPreview() {
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
}
