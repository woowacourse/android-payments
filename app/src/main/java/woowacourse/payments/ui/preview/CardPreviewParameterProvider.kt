package woowacourse.payments.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import woowacourse.payments.domain.Banks
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.core.BankType

class OneCardPreviewParameterProvider : PreviewParameterProvider<Card> {
    override val values: Sequence<Card>
        get() =
            sequenceOf(
                Card(
                    number = "1111222233334444",
                    expireDate = "0908",
                    ownerName = "peto",
                    password = "",
                    bank = BankType.Bank(Banks.BC),
                ),
            )
}

class CardsPreviewParameterProvider : PreviewParameterProvider<List<Card>> {
    override val values: Sequence<List<Card>>
        get() =
            sequenceOf(
                Banks.entries.map { company ->
                    Card(
                        number = "1111222233334444",
                        expireDate = "0908",
                        ownerName = "peto",
                        password = "",
                        bank = BankType.Bank(company),
                    )
                },
            )
}
