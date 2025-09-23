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
import woowacourse.payments.ui.model.CardCompanyUiModel
import woowacourse.payments.ui.model.cardCompanyMap

private const val COLUMN_COUNT = 4

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SelectedBankRow(
    selectedBank: (CardCompanyUiModel) -> Unit,
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
        repeat(cardCompanyMap.entries.filterNot { it == CardCompanyUiModel.Default() }.size) { index: Int ->
            if (selectedBank != CardCompanyUiModel.Default()) {
                val cardCompanyType = cardCompanyMap.entries.toList()[index].value
                SelectableCardCompanyLogo(
                    cardCompany = cardCompanyType,
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