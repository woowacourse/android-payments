package woowacourse.payments.ui.mapper

import woowacourse.payments.domain.ExpireDateStatus
import woowacourse.payments.domain.PaymentCard

sealed class CardCreationResult {
    data class Success(
        val paymentCard: PaymentCard,
    ) : CardCreationResult()

    object InvalidCardNumber : CardCreationResult()

    data class InvalidExpireDate(
        val status: ExpireDateStatus,
    ) : CardCreationResult()

    object InvalidOwnerName : CardCreationResult()

    object InvalidPassword : CardCreationResult()
}
