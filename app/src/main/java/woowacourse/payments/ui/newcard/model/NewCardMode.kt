package woowacourse.payments.ui.newcard.model

import woowacourse.payments.ui.model.CardUiModel

sealed interface NewCardMode {
    data object Create : NewCardMode
    data class Update(val cardUiModel: CardUiModel) : NewCardMode

    companion object {
        fun of(cardUiModel: CardUiModel?): NewCardMode =
            if (cardUiModel == null) Create
            else Update(cardUiModel)
    }
}