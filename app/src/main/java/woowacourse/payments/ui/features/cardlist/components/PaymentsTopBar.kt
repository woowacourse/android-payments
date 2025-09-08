package woowacourse.payments.ui.features.cardlist.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentsTopBar(
    modifier: Modifier = Modifier,
    onAddClick: () -> Unit,
    isAddButtonVisible: Boolean = false,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.card_list_top_bar_title),
                modifier = Modifier,
                style = MaterialTheme.typography.titleLarge,
            )
        },
        modifier = modifier,
        actions = {
            if (isAddButtonVisible) {
                TextButton(onClick = onAddClick) {
                    Text(
                        text = stringResource(R.string.card_list_top_bar_add_btn),
                        modifier = Modifier,
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
fun NewCardTopBarPreview() {
    AndroidpaymentsTheme {
        PaymentsTopBar(
            onAddClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NewCardTopBarWithAddBtnPreview() {
    AndroidpaymentsTheme {
        PaymentsTopBar(
            onAddClick = {},
            isAddButtonVisible = true,
        )
    }
}
