package woowacourse.payments.ui.newcard.state.holder

import woowacourse.payments.ui.model.BankUiModel
import woowacourse.payments.ui.model.CardUiModel

interface NewCardContentStateAction {
    val hasBank: Boolean
    fun newCard(cardId: Long? = null): CardUiModel

    fun updateCardBank(bankUiModel: BankUiModel)

    fun updateCardNumber(cardNumber: String)

    fun updateExpiryDate(expiryDate: String)

    fun updateOwnerName(ownerName: String)

    fun updatePassword(password: String)
}