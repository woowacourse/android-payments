package woowacourse.payments.ui.mapper

import woowacourse.payments.domain.PaymentCard
import woowacourse.payments.ui.model.PaymentCardUiModel
import java.time.format.DateTimeFormatter

fun PaymentCardUiModel.toDomain(): PaymentCard = PaymentCard(number, expirationDate, cardholderName)

fun PaymentCard.toUiModel(): PaymentCardUiModel =
    PaymentCardUiModel(
        number = number.toString(),
        expirationDate = expirationDate.format(DateTimeFormatter.ofPattern("MMyy")),
        cardholderName = cardholderName,
    )
