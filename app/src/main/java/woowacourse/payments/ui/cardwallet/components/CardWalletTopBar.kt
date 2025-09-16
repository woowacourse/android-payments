package woowacourse.payments.ui.cardwallet.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
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
import woowacourse.payments.designsystem.theme.AndroidpaymentsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardWalletTopBar(
    cardCount: Int,
    onAddClick: () -> Unit,
) {
    CenterAlignedTopAppBar(
        title = { Text(text = "Payments") },
        modifier = Modifier.fillMaxWidth(),
        actions = {
            if (cardCount >= 2) {
                Text(
                    text = stringResource(R.string.card_wallet_add),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W700,
                    modifier =
                        Modifier
                            .padding(horizontal = 8.dp)
                            .clickable { onAddClick() }
                            .padding(end = 12.dp),
                )
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun CardWalletTopBarPreview() {
    AndroidpaymentsTheme {
        CardWalletTopBar(
            cardCount = 3,
            onAddClick = {},
        )
    }
}
