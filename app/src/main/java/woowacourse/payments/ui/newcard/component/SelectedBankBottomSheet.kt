package woowacourse.payments.ui.newcard.component

import android.system.Os.close
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
import woowacourse.payments.ui.newcard.CardStateHolder
import woowacourse.payments.ui.newcard.uiModel.BankTypeUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectedBankBottomSheet(
    isVisible: Boolean,
    state: CardStateHolder,
    onDismissRequest: () -> Unit,
) {
    if (!isVisible) return

    val modalBottomSheetState = rememberModalBottomSheetState(
        confirmValueChange = { false }
    )

    var selectedBank by remember { mutableStateOf<BankTypeUiModel?>(null) }

    LaunchedEffect(selectedBank) {
        if (selectedBank != null) {
            modalBottomSheetState.hide()
            onDismissRequest()
            selectedBank = null
        }
    }

    ModalBottomSheet(
        sheetState = modalBottomSheetState,
        onDismissRequest = onDismissRequest,
    ) {
        SelectedBankRow(
            selectedBank = { bank ->
                state.changeBankType(bank)
                selectedBank = bank
            },
        )
    }
}

@Preview
@Composable
private fun SelectedBankBottomSheetPreview() {
    SelectedBankBottomSheet(isVisible = false, state = CardStateHolder(), {})
}