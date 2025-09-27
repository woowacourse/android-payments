package woowacourse

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
