package woowacourse.payments.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.domain.model.BankType

private const val COLUMN_COUNT = 4

@Composable
fun BankSelectRow(
    onSelect: (BankType) -> Unit,
    modifier: Modifier = Modifier,
) {
    val banks = remember { BankType.entries.filter { it != BankType.NOT_SELECTED } }

    FlowRow(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(4.dp)
                .background(color = Color.White),
        horizontalArrangement = Arrangement.Center,
        maxItemsInEachRow = COLUMN_COUNT,
    ) {
        banks.forEach { bank ->
            BankItem(
                bank = bank,
                onClick = { onSelect(bank) },
                modifier = Modifier.weight(1f),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BankSelectRowPreview() {
    BankSelectRow(
        onSelect = {},
    )
}
