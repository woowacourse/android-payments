package woowacourse.payments.ui.newcard.banks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.domain.BankType

private const val COLUMN_COUNT = 4

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Banks(
    onSelectCard: (BankType) -> Unit,
    modifier: Modifier = Modifier,
) {
    val bankTypes by remember { mutableStateOf(BankType.entries.filter { it != BankType.UNSPECIFIED }) }
    FlowRow(
        modifier =
            modifier
                .fillMaxWidth()
                .testTag(BanksTestTag.BANK_BOARD_TAG),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        maxItemsInEachRow = COLUMN_COUNT,
    ) {
        bankTypes.forEach {
            Bank(
                it,
                onSelectCard,
                Modifier.weight(1f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BanksPreview() {
    Banks({})
}
