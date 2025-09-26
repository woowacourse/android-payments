package woowacourse.payments.ui.cardform.state

import toUiModel
import woowacourse.payments.data.CardStorage
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.domain.CardExpirationDate
import woowacourse.payments.domain.CardExpirationDate.Companion.REQUIRE_CARD_EXPIRATION_DATE_LENGTH
import woowacourse.payments.domain.CardExpirationDateStatus
import woowacourse.payments.domain.CardExpirationErrorCode
import woowacourse.payments.ui.model.CardCompaniesUiModel
import woowacourse.payments.ui.model.CardUiModel

class CardFormViewModel {
    fun loadCard(cardId: Long): CardUiModel = CardStorage.findById(cardId).toUiModel()

    fun loadCardCompanies(): CardCompaniesUiModel {
        val cardCompanies = CardCompany.entries.map { it.toUiModel() }
        return CardCompaniesUiModel(items = cardCompanies)
    }

    fun validateCardExpirationDate(input: String): CardExpirationErrorCode? {
        if (input.length > REQUIRE_CARD_EXPIRATION_DATE_LENGTH) return null

        return when (
            val result: CardExpirationDateStatus =
                CardExpirationDate.toCardExpirationDateStatus(input)
        ) {
            is CardExpirationDateStatus.Success -> null
            is CardExpirationDateStatus.Error -> result.errorCode
        }
    }

    fun isModify(
        originCardUiModel: CardUiModel,
        updateCardUiModel: CardUiModel,
    ): Boolean = originCardUiModel != updateCardUiModel
}
