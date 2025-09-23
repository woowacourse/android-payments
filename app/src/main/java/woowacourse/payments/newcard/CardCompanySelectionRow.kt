package woowacourse.payments.newcard

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

private const val COLUMN_COUNT = 4

@Composable
fun CardCompanyItem(
    cardCompanyUiState: CardCompanyUiState,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier =
            modifier
                .padding(horizontal = 8.dp)
                .sizeIn(minWidth = 70.dp, minHeight = 70.dp),
    ) {
        Image(
            painter = painterResource(id = cardCompanyUiState.imageResId),
            contentDescription = stringResource(id = cardCompanyUiState.nameResId),
            modifier = Modifier.size(37.dp),
        )
        Text(
            text = stringResource(id = cardCompanyUiState.nameResId),
            fontSize = 16.sp,
            letterSpacing = (-0.085).em,
        )
    }
}

@Composable
fun CardCompanySelectionRow(
    modifier: Modifier = Modifier,
    onItemClick: (CardCompany) -> Unit = {},
) {
    FlowRow(
        horizontalArrangement = Arrangement.Center,
        verticalArrangement = Arrangement.spacedBy(20.dp),
        maxItemsInEachRow = COLUMN_COUNT,
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(vertical = 36.dp),
    ) {
        CardCompany.entries.forEach { cardCompany ->
            if (cardCompany != CardCompany.NONE) {
                CardCompanyItem(
                    cardCompanyUiState = CardCompanyUiState.from(cardCompany),
                    modifier = Modifier.clickable { onItemClick(cardCompany) },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardCompanySelectionRowPreview() {
    AndroidpaymentsTheme {
        CardCompanySelectionRow()
    }
}
