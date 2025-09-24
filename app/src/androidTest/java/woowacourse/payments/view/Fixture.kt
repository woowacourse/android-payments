package woowacourse.payments.view

val CardUiModel: CardUiModel =
    CardUiModel(
        number = "1234".repeat(4),
        expiredDate = "0421",
        holder = "CREW",
        holderMaxLength = 30,
        password = "1234",
        bankType = BankType.BC,
    )

fun cardUiModels(count: Int): List<CardUiModel> = if (count == 0) emptyList() else (1..count).map { CardUiModel }
