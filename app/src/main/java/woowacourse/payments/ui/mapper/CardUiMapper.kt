package woowacourse.payments.ui.mapper

import woowacourse.payments.domain.model.Card
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.text.CardNumberFormatter
import woowacourse.payments.ui.text.ExpirationDateFormatter

fun Card.toUiModel(): CardUiModel =
    CardUiModel(
        cardNumber = CardNumberFormatter.formatAndMask(cardNumber),
        expirationDate = ExpirationDateFormatter.format(expirationDate),
        userName = userName.value,
        password = password.value,
    )
