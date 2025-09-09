package woowacourse.payments.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.domain.PaymentCard
import woowacourse.payments.ui.model.PaymentCardUiModel

@Composable
fun MultiCards(
    paymentCards: List<PaymentCardUiModel>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        paymentCards.forEach { card ->
            PaymentCard(
                paymentCard = card,
                modifier =
                    Modifier
                        .padding(top = 16.dp),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MultiCardsPreview() {
    MultiCards(
        paymentCards =
            listOf(
                PaymentCardUiModel("1234123456785678", "1215", "minjeong"),
                PaymentCardUiModel("1111222233334444", "1234", "junseo"),
            ),
    )
}
