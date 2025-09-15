package woowacourse.payments.ui.newcard

data class NewCardUiState(
    val number: String = "",
    val expirationDate: String = "",
    val ownerName: String = "",
    val password: String = "",
)