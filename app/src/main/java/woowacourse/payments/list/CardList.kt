package woowacourse.payments.list

import androidx.compose.runtime.Composable
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.PaymentCard

@Composable
fun CardList(cards: List<Card>) {
    cards.forEach { card: Card -> PaymentCard(card = card) }
}