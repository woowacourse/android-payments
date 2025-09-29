package woowacourse.payments.ui.core.mapper

import woowacourse.payments.domain.Card
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.model.toBankType
import woowacourse.payments.ui.model.toLocalBankUiModel

fun Card.toCardUiModel() =
    CardUiModel(
        bankType.toLocalBankUiModel(),
        cardNumbers,
        cardExpiry,
        ownerName,
        id,
    )

fun CardUiModel.toCard(password: String) =
    Card(
        bankUiModel.toBankType(),
        cardNumbers,
        cardExpiry,
        ownerName,
        password,
        id,
    )
