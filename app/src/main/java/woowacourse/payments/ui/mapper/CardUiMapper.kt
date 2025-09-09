package woowacourse.payments.ui.mapper

import woowacourse.payments.domain.model.Card
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.text.formatExpirationDate
import woowacourse.payments.ui.text.maskAndFormatCardNumber

fun Card.toUiModel(): CardUiModel =
    CardUiModel(
        cardNumber = cardNumber.maskAndFormatCardNumber(),
        expirationDate = expirationDate.formatExpirationDate(),
        userName = userName,
        password = password,
    )
