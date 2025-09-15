package woowacourse.payments.ui.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.components.PaymentCard
import woowacourse.payments.ui.components.PaymentCreateCard
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.preview.paymentCardUiModelSample

@Composable
fun SingleCardsSection(
    onAddClick: () -> Unit,
    card: PaymentCardUiModel,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(36.dp),
        modifier = modifier,
    ) {
        Spacer(Modifier.height(12.dp))
        PaymentCard(card)
        PaymentCreateCard(onAddClick)
    }
}

@Preview(showBackground = true)
@Composable
fun SingleCardsSectionPreview() {
    SingleCardsSection({}, paymentCardUiModelSample)
}
