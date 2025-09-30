package woowacourse.payments.ui.addcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.domain.BankType

private const val COLUMN_COUNT = 4

@Composable
fun BankSelectRow(
    onClick: (BankType) -> Unit,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(47.dp, 55.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        maxItemsInEachRow = COLUMN_COUNT,
        verticalArrangement = Arrangement.spacedBy(23.dp),
    ) {
        val bankList = BankType.entries.filter { it != BankType.NOT_SELECTED }
        bankList.forEach { bank ->
            BankSelectBtn(
                bank = bank.toUiModel(),
                onClick = { onClick(bank) },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BankSelectRowPreview() {
    BankSelectRow(onClick = {})
}
