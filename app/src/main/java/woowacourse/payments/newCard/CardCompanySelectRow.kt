package woowacourse.payments.newCard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

private const val COLUMN_COUNT = 4

@Composable
fun CardCompanySelectRow(
    onClick: (CardCompanyUiModel) -> Unit,
    companies: List<CardCompanyUiModel>,
) {
    FlowRow(
        modifier = Modifier
            .width(360.dp)
            .height(227.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalArrangement = Arrangement.SpaceEvenly,
        maxItemsInEachRow = COLUMN_COUNT,
    ) {
        companies.forEach { company ->
            CardCompanyItem(
                company,
                modifier = Modifier
                    .width(80.dp)
                    .clickable { onClick(company) }
            )
        }
    }
}

@Preview
@Composable
private fun CardCompanySelectRowPreview() {
    AndroidpaymentsTheme {
        CardCompanySelectRow(
            onClick = {},
            companies = CardCompany.entries
                .filter { it != CardCompany.NOT_SELECTED }
                .map { it.toUiModel() })
    }
}
