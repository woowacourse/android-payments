package woowacourse.payments.ui.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.components.AddCardButton
import woowacourse.payments.ui.components.PaymentCard
import woowacourse.payments.ui.model.CardUiModel

@Composable
fun PaymentList(
    cards: List<CardUiModel>,
    canAddMore: Boolean,
    onAddCardClick: () -> Unit,
) {
    cards.forEachIndexed { index, card ->
        PaymentCard(card = card)
        if (index < cards.lastIndex) Spacer(Modifier.height(16.dp))
    }
    if (canAddMore) {
        Spacer(Modifier.height(24.dp))
        AddCardButton(onClick = onAddCardClick)
    }
}
