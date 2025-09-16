package woowacourse.payments.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.domain.model.BankType
import woowacourse.payments.ui.model.BankUiModel
import woowacourse.payments.ui.model.toUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

private const val COLUMN_COUNT = 4
private const val ROW_COUNT = 2

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BankSelectRow(
    onSelect: (BankType) -> Unit,
    modifier: Modifier = Modifier,
    banks: List<BankUiModel> =
        remember {
            BankType.entries
                .filter { it != BankType.NOT_SELECTED }
                .map { it.toUiModel() }
        },
) {
    val maxItems = COLUMN_COUNT * ROW_COUNT
    val items = remember(banks, COLUMN_COUNT, ROW_COUNT) { banks.take(maxItems) }

    Box(
        modifier =
            modifier
                .fillMaxWidth()
                .height(227.dp),
        contentAlignment = Alignment.Center,
    ) {
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(23.dp, Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.spacedBy(23.dp),
            maxItemsInEachRow = COLUMN_COUNT,
        ) {
            items.forEach { bank ->
                BankItem(
                    bank = bank,
                    onClick = { onSelect(bank.type) },
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "BankSelectRow")
@Composable
private fun BankSelectRowPreview() {
    AndroidpaymentsTheme {
        BankSelectRow(
            onSelect = {},
        )
    }
}
