package woowacourse.payments.ui.screen

import woowacourse.payments.ui.model.CardUiModel

val DEFAULT_CARD =
    CardUiModel(
        number = "1234567812345678",
        expiredDate = "0925",
        ownerName = "INHYEOP LEE",
    )

val SINGLE_CARD = listOf(DEFAULT_CARD)

val MULTIPLE_CARD =
    listOf(
        DEFAULT_CARD,
        CardUiModel(
            number = "0000000000000000",
            expiredDate = "1025",
            ownerName = "BEOMJUN HAM",
        ),
    )
