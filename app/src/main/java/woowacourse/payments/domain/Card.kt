package woowacourse.payments.domain

import woowacourse.payments.ui.state.BankState

data class Card(
    val number: String,
    val expireDate: String,
    val ownerName: String,
    val password: String,
    val bank: BankState,
) {
    constructor(bank: BankState.Bank) : this(
        number = EMPTY_NUMBER,
        expireDate = EMPTY_EXPIRE_DATE,
        ownerName = EMPTY_OWNER_NAME,
        password = EMPTY_PASSWORD,
        bank = bank,
    )

    companion object {
        const val CARD_MAX_LENGTH = 16
        const val CARD_NUMBER_MASKING_LENGTH = 8
        private const val EMPTY_NUMBER = ""
        private const val EMPTY_EXPIRE_DATE = ""
        private const val EMPTY_OWNER_NAME = ""
        private const val EMPTY_PASSWORD = ""

        val Empty =
            Card(
                number = EMPTY_NUMBER,
                expireDate = EMPTY_EXPIRE_DATE,
                ownerName = EMPTY_OWNER_NAME,
                password = EMPTY_PASSWORD,
                bank = BankState.Empty,
            )
    }
}
