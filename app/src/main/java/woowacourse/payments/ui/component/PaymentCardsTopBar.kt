package woowacourse.payments.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentCardsTopBar(
    cardCount: Int,
    modifier: Modifier = Modifier,
    onAddClick: () -> Unit = {},
) {
    CenterAlignedTopAppBar(
        title = { Text(stringResource(R.string.title_payment_cards)) },
        actions = {
            if (cardCount >= 2) {
                Text(
                    text = stringResource(R.string.payment_card_top_bar_add_card),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier =
                        Modifier
                            .padding(end = 20.dp)
                            .clickable(onClick = onAddClick),
                )
            }
        },
        modifier = modifier,
    )
}

@Preview
@Composable
fun MultiPaymentCardsTopBarPreview() {
    PaymentCardsTopBar(2)
}

@Preview
@Composable
fun SinglePaymentCardsTopBarPreview() {
    PaymentCardsTopBar(1)
}
