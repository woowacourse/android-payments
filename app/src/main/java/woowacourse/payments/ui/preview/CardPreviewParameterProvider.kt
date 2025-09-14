package woowacourse.payments.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import woowacourse.payments.domain.Card

class OneCardPreviewParameterProvider : PreviewParameterProvider<Card> {
    override val values: Sequence<Card>
        get() =
            sequenceOf(
                Card(
                    number = "1111222233334444",
                    expireDate = "0908",
                    ownerName = "peto",
                    password = "",
                ),
            )
}

class CardsPreviewParameterProvider : PreviewParameterProvider<List<Card>> {
    override val values: Sequence<List<Card>>
        get() =
            sequenceOf(
                listOf(
                    Card(
                        number = "1111222233334444",
                        expireDate = "0908",
                        ownerName = "peto",
                        password = "",
                    ),
                    Card(
                        number = "1111222233334444",
                        expireDate = "0908",
                        ownerName = "peto",
                        password = "",
                    ),
                    Card(
                        number = "1111222233334444",
                        expireDate = "0908",
                        ownerName = "peto",
                        password = "",
                    ),
                ),
            )
}
