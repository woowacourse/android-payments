package woowacourse.payments.ui.screen

import android.content.Intent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.domain.PaymentCard
import woowacourse.payments.ui.component.payments.PaymentsColumn
import woowacourse.payments.ui.component.payments.PaymentsTopBar


@Composable
fun PaymentScreen(
    modifier: Modifier = Modifier,
    cards: List<PaymentCard>,
    onAddNewCardClick: () -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = { PaymentsTopBar(onAddNewCardClick = { onAddNewCardClick() }) }
    ) { paddingValues ->
        PaymentsColumn(cards, modifier = Modifier.padding(paddingValues))
    }
}

@Preview
@Composable
private fun PaymentScreenPreview() {
    PaymentScreen(cards = emptyList(), onAddNewCardClick = {})
}