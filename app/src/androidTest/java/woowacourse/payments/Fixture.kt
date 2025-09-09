package woowacourse.payments

val NO_CARD = emptyArray<Card>()
val ONE_CARD =
    arrayOf(
        Card(
            number = "1234".repeat(4),
            owner = "CREW",
            expiredDate = "0421",
        ),
    )
val THREE_CARD =
    arrayOf(
        Card(
            number = "1234".repeat(4),
            owner = "CREW",
            expiredDate = "0421",
        ),
        Card(
            number = "1234".repeat(4),
            owner = "CREW",
            expiredDate = "0421",
        ),
        Card(
            number = "1234".repeat(4),
            owner = "CREW",
            expiredDate = "0421",
        ),
    )
