package woowacourse.payments.ui.cardcatalog.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.newcard.uiModel.toUiModel

@Composable
fun PaymentCards(
    cards: List<Card>,
    modifier: Modifier = Modifier,
) {
    for (card in cards) {
        PaymentCard(
            card = card,
            cardCompanyUiModel = card.cardCompany.toUiModel()
        )
        Spacer(modifier = modifier.height(36.dp))
    }
}