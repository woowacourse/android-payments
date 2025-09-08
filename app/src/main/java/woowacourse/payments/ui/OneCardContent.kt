package woowacourse.payments.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun OneCardContent(
    card: Card,
    addCard: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(36.dp),
    ) {
        PaymentCard(
            detail = card,
            modifier = Modifier.padding(top = 12.dp),
        )
        PaymentCardAdditionButton(
            onClick = addCard,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun OneCardContentPreview() {
    OneCardContent(
        card =
            Card(
                number = "1234".repeat(4),
                owner = "CREW",
                expiredDate = "0421",
            ),
        addCard = {},
    )
}
