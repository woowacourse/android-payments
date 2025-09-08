package woowacourse.payments.ui.screen.cards.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.common.component.CardChip
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.screen.cardAddition.component.PaymentCard
import woowacourse.payments.ui.theme.Gray20

@Composable
fun ExistingCard(
    card: CardUiModel,
    modifier: Modifier = Modifier,
) {
    PaymentCard(
        modifier = modifier,
        backgroundColor = Gray20,
        cardContent = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Bottom,
            ) {
                CardChip(modifier = Modifier.align(Alignment.Start))
                Spacer(modifier = Modifier.height(8.dp))
                CardInfo(card)
            }
        }
    )
}

@Preview
@Composable
private fun ExistingCardPreview() {
    ExistingCard(
        card = CardUiModel(
            number = "1234567812345678",
            expiredDate = "0925",
            ownerName = "INHYEOP LEE",
        )
    )
}
