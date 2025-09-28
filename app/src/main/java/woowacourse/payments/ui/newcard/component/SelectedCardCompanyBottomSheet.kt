package woowacourse.payments.ui.newcard.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.ui.model.CardCompanyUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectedCardCompanyBottomSheet(
    changeBottomSheet: () -> Unit,
    selectedCardCompany: (CardCompanyUiModel) -> Unit,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState()
) {
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = changeBottomSheet,
    ) {
        SelectedBankRow(
            selectedBank = { selectedCard ->
                selectedCardCompany(selectedCard)
                changeBottomSheet()
            },
            modifier = modifier,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun SelectedCardCompanyBottomSheetPreview() {
    SelectedCardCompanyBottomSheet(
        changeBottomSheet = { },
        selectedCardCompany = {},
        sheetState = SheetState(
            skipPartiallyExpanded = true,
            density = LocalDensity.current,
            initialValue = SheetValue.Expanded,
        )
    )
}