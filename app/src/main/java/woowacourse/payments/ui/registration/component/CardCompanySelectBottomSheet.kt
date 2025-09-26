@file:OptIn(ExperimentalMaterial3Api::class)

package woowacourse.payments.ui.registration.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.ui.model.CardCompaniesUiModel
import woowacourse.payments.ui.model.CardCompanyUiModel

@Composable
fun CardCompanySelectBottomSheet(
    cardCompanies: CardCompaniesUiModel,
    onCardCompanyClick: (CardCompanyUiModel) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
    ) {
        CardCompanySelectRow(
            cardCompanies = cardCompanies,
            onCardCompanyClick = onCardCompanyClick,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CardCompanySelectBottomSheetPreview() {
    CardCompanySelectBottomSheet(
        cardCompanies = CardCompaniesUiModel(emptyList()),
        onCardCompanyClick = {},
        onDismissRequest = {},
    )
}
