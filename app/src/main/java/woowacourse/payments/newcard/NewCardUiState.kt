package woowacourse.payments.newcard

data class NewCardUiState(
    val cardNumber: String = "",
    val expiredDate: String = "",
    val ownerName: String = "",
    val password: String = "",
    val isCardCompanySelected: Boolean = false,
)
