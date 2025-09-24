package woowacourse.payments.ui.newcard.model

import woowacourse.payments.ui.common.model.CardUiModel

sealed interface CardUpdateType {
    data object Add : CardUpdateType

    data class Edit(
        val card: CardUiModel,
    ) : CardUpdateType
}
