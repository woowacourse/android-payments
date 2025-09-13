package woowacourse.payments

val CARD =
    Card(
        number = "1234".repeat(4),
        owner = "CREW",
        expiredDate = "0421",
    )

val NO_CARD: List<Card> = emptyList()
val ONE_CARD: List<Card> =
    listOf(
        Card(
            number = "1234".repeat(4),
            owner = "CREW",
            expiredDate = "0421",
        ),
    )
val THREE_CARD: List<Card> =
    listOf(
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
