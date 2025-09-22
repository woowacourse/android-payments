package woowacourse.payments.ui.mapper

import woowacourse.payments.domain.card.PaymentCard
import woowacourse.payments.ui.model.ExpireDateStatus

sealed class CardCreationResult {
    data class Success(
        val paymentCard: PaymentCard,
    ) : CardCreationResult()

    object UnknownCardCompany : CardCreationResult()

    object InvalidCardNumber : CardCreationResult()

    data class InvalidExpireDate(
        val status: ExpireDateStatus,
    ) : CardCreationResult()

    object InvalidOwnerName : CardCreationResult()

    object InvalidPassword : CardCreationResult()
}
