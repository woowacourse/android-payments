package woowacourse.payments.ui.cardcatalog.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import woowacourse.payments.domain.Card

@Composable
fun PaymentCards(
    cards: List<Card>,
    modifier: Modifier = Modifier,
) {
    for (card in cards) {
        PaymentCard(
            card = card,
        )
        Spacer(modifier = modifier.height(36.dp))
    }
}