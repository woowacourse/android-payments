package woowacourse.payments.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.screen.PaymentEmpty
import woowacourse.payments.ui.screen.PaymentList

@Composable
fun PaymentCards(
    cards: List<CardUiModel>,
    canAddMore: Boolean,
    onAddCardClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(32.dp))
        if (cards.isEmpty()) {
            PaymentEmpty(onAddCardClick)
        } else {
            PaymentList(
                cards = cards,
                canAddMore = canAddMore,
                onAddCardClick = onAddCardClick,
            )
        }
    }
}
