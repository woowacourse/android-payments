package woowacourse.payments.ui.view.new

import woowacourse.payments.domain.Card

data class NewCardUiState(
    val card: Card = Card.Empty,
)
