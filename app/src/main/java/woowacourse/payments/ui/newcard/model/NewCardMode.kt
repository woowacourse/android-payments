package woowacourse.payments.ui.newcard.model

import woowacourse.payments.ui.model.PaymentCardUiModel

sealed interface NewCardMode {
    data object Create : NewCardMode
    data class Update(val cardUiModel: PaymentCardUiModel) : NewCardMode

    companion object {
        fun of(cardUiModel: PaymentCardUiModel?): NewCardMode =
            if (cardUiModel == null) Create
            else Update(cardUiModel)
    }
}