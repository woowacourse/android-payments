package woowacourse.payments.ui.payments.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.payments.model.BankUiState
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

private const val COLUMN_COUNT = 4

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BankSelectRow(
    onClick: (BankUiState) -> Unit,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        modifier =
            modifier
                .padding(4.dp)
                .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalArrangement = Arrangement.spacedBy(23.dp),
        maxItemsInEachRow = COLUMN_COUNT,
    ) {
        val selectableBanks = BankUiState.entries.filter { it != BankUiState.NOT_SELECTED }

        selectableBanks.forEach { bankUiState ->
            BankButton(
                bankUiState = bankUiState,
                modifier =
                    Modifier
                        .clickable { onClick(bankUiState) }
                        .weight(1f),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreViewSomeThing() {
    AndroidpaymentsTheme {
        Scaffold { innerPadding ->
            BankSelectRow({}, modifier = Modifier.padding(innerPadding))
        }
    }
}
