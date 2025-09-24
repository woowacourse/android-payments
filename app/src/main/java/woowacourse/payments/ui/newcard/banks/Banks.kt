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
import woowacourse.payments.domain.BankType
import woowacourse.payments.ui.model.BankUiModel
import woowacourse.payments.ui.model.toLocalBankUiModel

private const val COLUMN_COUNT = 4

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Banks(
    onSelectCard: (BankUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    val bankUiModels by remember { mutableStateOf(BankType.entries.map { it.toLocalBankUiModel() }) }
    FlowRow(
        modifier =
            modifier
                .fillMaxWidth()
                .testTag(BanksTestTag.BANK_BOARD_TAG),
        verticalArrangement = Arrangement.SpaceEvenly,
        maxItemsInEachRow = COLUMN_COUNT,
    ) {
        bankUiModels.forEach {
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
