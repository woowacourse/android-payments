package woowacourse.payments.ui.newcard.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.newcard.uiModel.BankTypeUiModel

private const val COLUMN_COUNT = 4
private const val ROW_COUNT = 2

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SelectedBankRow(
    selectedBank: (BankTypeUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        modifier = modifier
            .padding(43.dp)
            .height(227.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalArrangement = Arrangement.SpaceEvenly,
        maxItemsInEachRow = COLUMN_COUNT,
    ) {
        repeat(ROW_COUNT * COLUMN_COUNT) { index: Int ->
            if (BankTypeUiModel.entries[index] != BankTypeUiModel.NOT_SELECTED) {
                val bankType = BankTypeUiModel.entries[index]
                BankLogo(
                    bankType = bankType,
                    selectedBank = { selectedBank -> selectedBank(selectedBank) })
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Preview(showBackground = true)
@Composable
fun SelectedBankRowPreview() {
    SelectedBankRow({})
}