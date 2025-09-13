package woowacourse.payments.domain.card

import woowacourse.payments.domain.card.values.CardNumber
import woowacourse.payments.domain.card.values.ExpireDate
import woowacourse.payments.domain.card.values.OwnerName
import woowacourse.payments.domain.card.values.Password

data class PaymentCard(
    val cardNumber: CardNumber,
    val expireDate: ExpireDate,
    val ownerName: OwnerName,
    val password: Password,
)
