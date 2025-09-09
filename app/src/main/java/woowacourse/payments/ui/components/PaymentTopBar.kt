package woowacourse.payments.ui.components

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
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentTopBar(
    modifier: Modifier = Modifier,
    onAddClick: () -> Unit,
) {
    CenterAlignedTopAppBar(
        actions = {
            Text(
                text = "추가",
                modifier =
                    Modifier
                        .padding(horizontal = 20.dp)
                        .clickable(onClick = onAddClick),
                fontSize = 18.sp,
                fontWeight = FontWeight.W700,
            )
        },
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
            onAddClick = {},
        )
    }
}
