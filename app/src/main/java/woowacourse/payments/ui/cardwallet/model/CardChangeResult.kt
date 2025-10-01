package woowacourse.payments.ui.cardwallet.model

import woowacourse.payments.ui.common.model.CardUiModel

sealed interface CardChangeResult {
    data class Created(
        val card: CardUiModel,
    ) : CardChangeResult

    data class Edited(
        val card: CardUiModel,
    ) : CardChangeResult
}
