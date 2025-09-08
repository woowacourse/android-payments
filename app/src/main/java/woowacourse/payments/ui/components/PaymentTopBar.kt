package woowacourse.payments.ui.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentTopBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = { Text(stringResource(R.string.payment_top_bar_title)) },
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
private fun PaymentTopBarPreview() {
    AndroidpaymentsTheme {
        PaymentTopBar(
            modifier = Modifier,
        )
    }
}
