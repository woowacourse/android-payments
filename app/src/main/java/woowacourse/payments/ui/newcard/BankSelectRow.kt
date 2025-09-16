package woowacourse.payments.ui.newcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private const val COLUMN_COUNT = 4
private const val ROW_COUNT = 2

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BankSelectRow(onClick: () -> Unit) {
    FlowRow(
        modifier = Modifier.padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        maxItemsInEachRow = COLUMN_COUNT
    ) {
        repeat(ROW_COUNT * COLUMN_COUNT) {
            // ...
        }
    }
}