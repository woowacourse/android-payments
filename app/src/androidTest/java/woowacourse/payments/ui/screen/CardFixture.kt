package woowacourse.payments.ui.screen

import woowacourse.payments.ui.model.CardUiModel

val DEFAULT_CARD = CardUiModel(
    number = "1234567812345678",
    expiredDate = "0925",
    ownerName = "INHYEOP LEE",
)

val SINGLE_CARD = listOf(DEFAULT_CARD)

val MULTIPLE_CARD = List(3) { DEFAULT_CARD }
