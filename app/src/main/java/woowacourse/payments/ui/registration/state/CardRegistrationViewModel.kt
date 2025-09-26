package woowacourse.payments.ui.registration.state

import woowacourse.payments.domain.CardExpirationDate
import woowacourse.payments.domain.CardExpirationDate.Companion.REQUIRE_CARD_EXPIRATION_DATE_LENGTH
import woowacourse.payments.domain.CardExpirationDateStatus
import woowacourse.payments.domain.CardExpirationErrorCode

class CardRegistrationViewModel {
    fun validateCardExpirationDate(input: String): CardExpirationErrorCode? {
        if (input.length > REQUIRE_CARD_EXPIRATION_DATE_LENGTH) return null

        return when (val result: CardExpirationDateStatus = CardExpirationDate.from(input)) {
            is CardExpirationDateStatus.Success -> null
            is CardExpirationDateStatus.Error -> result.errorCode
        }
    }
}
