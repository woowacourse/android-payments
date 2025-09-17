package woowacourse.payments

val CARD =
    Card(
        number = "1234".repeat(4),
        owner = "CREW",
        expiredDate = "0421",
        bankType = BankType.BC,
    )

fun cards(count: Int): List<Card> = if (count == 0) emptyList() else (1..count).map { CARD }
