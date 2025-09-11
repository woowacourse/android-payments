package woowacourse.payments.ui.features.addcard

import woowacourse.payments.domain.ExpireDate
import woowacourse.payments.domain.ExpireDateStatus.Invalid.ExpireDateInvalidReason

sealed interface ExpireDateUiState {
    data class Valid(
        val expireDate: ExpireDate,
    ) : ExpireDateUiState

    data class Invalid(
        val reason: ExpireDateInvalidReason,
    ) : ExpireDateUiState

    data object Empty : ExpireDateUiState

    data object Typing : ExpireDateUiState
}
