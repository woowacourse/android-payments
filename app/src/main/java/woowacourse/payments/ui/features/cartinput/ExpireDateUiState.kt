package woowacourse.payments.ui.features.cartinput

import woowacourse.payments.ui.model.ExpireDateStatus.Invalid.ExpireDateInvalidReason

sealed interface ExpireDateUiState {
    data object Valid : ExpireDateUiState

    data class Invalid(
        val reason: ExpireDateInvalidReason,
    ) : ExpireDateUiState

    data object Empty : ExpireDateUiState

    data object Typing : ExpireDateUiState
}
