package woowacourse.payments.domain

import woowacourse.payments.ui.model.CardUiModel

data class Card(
    val number: CardNumber = CardNumber(),
    val expirationDate: CardExpirationDate = CardExpirationDate(),
    val ownerName: OwnerName = OwnerName(),
    val password: Password = Password(),
    val bank: BankType = BankType.NOT_SELECTED,
) {
    fun isValid(): Boolean = number.isValid() && expirationDate.isValid() && password.isValid() && bank != BankType.NOT_SELECTED

    fun toUiModel() =
        CardUiModel(
            number = number.toUiModel(),
            expirationDate = expirationDate.toUiModel(),
            ownerName = ownerName.toUiModel(),
            password = password.toUiModel(),
            bank = bank.toUiModel(),
        )
}
