package woowacourse.payments.ui.features.addcard

data class CardUiState(
    val cardNumber: String = "",
    val expireDate: String = "",
    val ownerName: String = "",
    val password: String = "",
) {
    fun updateCardNumber(cardNumber: String): CardUiState = this.copy(cardNumber = cardNumber)

    fun updateExpireDate(expireDate: String): CardUiState = this.copy(expireDate = expireDate)

    fun updateOwnerName(ownerName: String): CardUiState = this.copy(ownerName = ownerName)

    fun updatePassword(password: String): CardUiState = this.copy(password = password)
}
