package woowacourse.payments.ui.newcard.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.ui.newcard.state.CardStateHolder
import woowacourse.payments.ui.newcard.uiModel.CardCompanyUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectedBankBottomSheet(
    state: CardStateHolder,
    onDismissRequest: () -> Unit,
) {
    var selectedBankCard: CardCompanyUiModel by remember { mutableStateOf(CardCompanyUiModel.Default()) }

    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
        confirmValueChange = { false },
    )

    LaunchedEffect(key1 = selectedBankCard) {
        if (selectedBankCard != CardCompanyUiModel.Default()) {
            bottomSheetState.hide()
            onDismissRequest()
        }
    }
    ModalBottomSheet(
        sheetState = bottomSheetState,
        onDismissRequest = { onDismissRequest() },
    ) {
        SelectedBankRow(
            selectedBank = { selectedBank ->
                state.selectedCardCompany(selectedBank)
                selectedBankCard = selectedBank
            },
        )
    }
}

@Preview
@Composable
private fun SelectedBankBottomSheetPreview() {
    SelectedBankBottomSheet(state = CardStateHolder(), {})
}