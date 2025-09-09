package woowacourse.payments.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.model.PaymentCard

@Composable
fun SingleCard(
    paymentCard: PaymentCard,
    onAddCard: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        PaymentCard(modifier = Modifier.padding(top = 12.dp, bottom = 36.dp), paymentCard)
        AddCard(onAddClick = onAddCard)
    }
}

@Preview(showBackground = true)
@Composable
fun SingleCardPreview() {
    SingleCard(onAddCard = {}, paymentCard = PaymentCard("1234567812345678", "0511", "minjeong"))
}
