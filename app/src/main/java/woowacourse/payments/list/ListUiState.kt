package woowacourse.payments.list

import woowacourse.payments.domain.Card

data class ListUiState(val cards: List<Card> = emptyList())