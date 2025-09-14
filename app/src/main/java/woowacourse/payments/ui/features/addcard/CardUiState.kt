package woowacourse.payments.ui.features.addcard

import woowacourse.payments.ui.model.CardCompanyUiModel

data class CardUiState(
    val cardNumber: String = "",
    val expireDate: String = "",
    val ownerName: String = "",
    val password: String = "",
    val cardCompanyUiModel: CardCompanyUiModel = CardCompanyUiModel.UNKNOWN,
) {
    fun updateCardNumber(cardNumber: String): CardUiState = this.copy(cardNumber = cardNumber)

    fun updateExpireDate(expireDate: String): CardUiState = this.copy(expireDate = expireDate)

    fun updateOwnerName(ownerName: String): CardUiState = this.copy(ownerName = ownerName)

    fun updatePassword(password: String): CardUiState = this.copy(password = password)

    fun updateCardCompany(cardCompanyUiModel: CardCompanyUiModel): CardUiState = this.copy(cardCompanyUiModel = cardCompanyUiModel)
}
