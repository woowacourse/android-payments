package woowacourse.payments.ui.newcard.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.ui.newcard.model.CardCompanyUiModel
import woowacourse.payments.ui.newcard.model.toUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompanySelectBottomSheet(
    companies: List<CardCompanyUiModel>,
    onCompanySelected: (CardCompanyUiModel) -> Unit,
    sheetState: SheetState = rememberModalBottomSheetState(),
    onDisMiss: () -> Unit = {},
) {
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDisMiss,
    ) {
        CompanySelectRow(
            companies = companies,
            onCompanySelected = onCompanySelected,
            modifier = Modifier.padding(top = 60.dp, bottom = 80.dp),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun CompanySelectBottomSheetPreview() {
    CompanySelectBottomSheet(
        companies = CardCompany.entries.map(CardCompany::toUiModel),
        onCompanySelected = {},
        sheetState =
            rememberStandardBottomSheetState(
                initialValue = SheetValue.Expanded,
            ),
    )
}
