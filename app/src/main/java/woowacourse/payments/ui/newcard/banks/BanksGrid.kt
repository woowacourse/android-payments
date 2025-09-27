package woowacourse.payments.ui.newcard.banks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.FlowRowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.domain.BankType
import woowacourse.payments.ui.model.BankUiModel
import woowacourse.payments.ui.model.toLocalBankUiModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BanksGrid(
    banks: List<BankUiModel>,
    columnCount: Int,
    modifier: Modifier = Modifier,
    item: @Composable FlowRowScope.(BankUiModel) -> Unit
) {
    FlowRow(
        modifier =
            modifier
                .fillMaxWidth()
                .testTag(BanksTestTag.BANK_BOARD_TAG),
        verticalArrangement = Arrangement.SpaceEvenly,
        maxItemsInEachRow = columnCount,
    ) {
        banks.forEach { bank -> item(bank) }
    }
}

@Preview(showBackground = true)
@Composable
fun BanksPreview() {
    val banks = BankType.entries.map { it.toLocalBankUiModel() }
    BanksGrid(
        banks,
        4,
    ) { bank ->
        Bank(
            bankUiModel = bank,
            onSelectBank = {}, // 상위 캡처
            modifier = Modifier
                .weight(1f, fill = true)
                .fillMaxWidth()
        )
    }
}
