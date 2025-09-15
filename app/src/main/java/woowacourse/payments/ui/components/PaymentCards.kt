package woowacourse.payments.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R
import woowacourse.payments.ui.model.CardUiModel

@Composable
fun PaymentCards(
    cards: List<CardUiModel>,
    onAddCardClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(32.dp))

        if (cards.isEmpty()) {
            Text(
                text = stringResource(R.string.payment_add_new_card_prompt),
                fontSize = 18.sp,
                fontWeight = FontWeight.W700,
            )
            Spacer(Modifier.height(32.dp))
            AddCardButton(onClick = onAddCardClick)
        } else {
            cards.forEachIndexed { index, card ->
                PaymentCard(card = card)
                if (index < cards.lastIndex) {
                    Spacer(Modifier.height(16.dp))
                }
            }
            if (cards.size < 2) {
                Spacer(Modifier.height(24.dp))
                AddCardButton(onClick = onAddCardClick)
            }
        }
    }
}
