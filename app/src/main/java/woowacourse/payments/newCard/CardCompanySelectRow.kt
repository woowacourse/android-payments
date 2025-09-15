package woowacourse.payments.newCard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import woowacourse.payments.domain.CardCompany

private const val COLUMN_COUNT = 4

@Composable
fun CardCompanySelectRow(
    onClick: (CardCompanyUiModel) -> Unit,
) {
    val companies = CardCompany.entries
        .filter { it != CardCompany.NOT_SELECTED }
        .map { it.toUiModel() }

    FlowRow(
        modifier = Modifier.padding(vertical = 30.dp, horizontal = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        maxItemsInEachRow = COLUMN_COUNT
    ) {
        companies.forEach { company ->
            CardCompanyItem(
                company,
                onClick = { onClick(company) },
            )
        }
    }
}

