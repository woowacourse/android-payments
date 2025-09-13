package woowacourse.payments.list

import androidx.compose.runtime.Composable
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.PaymentCard

@Composable
fun CardList(cards: List<CardUiModel>) {
    cards.forEach { card: CardUiModel -> PaymentCard(card = card) }
}