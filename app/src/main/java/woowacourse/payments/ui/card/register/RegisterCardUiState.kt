package woowacourse.payments.ui.card.register

import woowacourse.payments.domain.Bank

data class RegisterCardUiState(
    val cardNumber: String = "",
    val expirationDate: String = "",
    val cardHolderName: String = "",
    val password: String = "",
    val selectedBank: Bank? = null,
    val showBottomSheet: Boolean = true,
)
