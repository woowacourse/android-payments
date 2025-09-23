package woowacourse.payments.ui.registration.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.model.CardCompanyUiModel

private const val COLUMN_COUNT = 4

@Composable
fun CardCompanySelectRow(
    onCardCompanyClick: (CardCompanyUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        modifier =
            modifier
                .padding(vertical = 36.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        maxItemsInEachRow = COLUMN_COUNT,
    ) {
        CardCompanyUiModel.entries
            .filter { cardCompany: CardCompanyUiModel -> cardCompany != CardCompanyUiModel.NOT_SELECT }
            .forEach { cardCompany: CardCompanyUiModel ->
                CardCompanyItem(
                    companyLogo = painterResource(cardCompany.image),
                    companyName = stringResource(cardCompany.companyName),
                    modifier =
                        Modifier
                            .fillMaxWidth(1f / COLUMN_COUNT)
                            .clickable { onCardCompanyClick(cardCompany) },
                )
            }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardCompanySelectRowPreview() {
    CardCompanySelectRow(
        onCardCompanyClick = {},
    )
}
