package woowacourse.payments.view

import woowacourse.payments.view.ui.model.BankTypeUiModel
import woowacourse.payments.view.ui.model.CardUiModel

val CardUiModel: CardUiModel =
    CardUiModel(
        number = "1234".repeat(4),
        expiredDate = "0421",
        holder = "CREW",
        holderMaxLength = 30,
        password = "1234",
        bankType = BankTypeUiModel.BC,
    )

fun cardUiModels(count: Int): List<CardUiModel> = if (count == 0) emptyList() else (1..count).map { CardUiModel }
