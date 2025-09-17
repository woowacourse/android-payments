package woowacourse.payments.ui.payments.component

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
import woowacourse.payments.ui.payments.model.BankUiState
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BankSelectBottomSheet(
    onDismissRequest: () -> Unit,
    onBankSelected: (BankUiState) -> Unit,
) {
    val sheetState =
        rememberModalBottomSheetState(
            confirmValueChange = { false },
        )
    var selectedBank by remember {
        mutableStateOf(BankUiState.NOT_SELECTED)
    }
    LaunchedEffect(key1 = selectedBank) {
        if (selectedBank != BankUiState.NOT_SELECTED) {
            sheetState.hide()
            onBankSelected(selectedBank)
        }
    }

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = { onDismissRequest() },
    ) {
        BankSelectRow(onClick = { bank ->
            selectedBank = bank
        })
    }
}

@Preview(showBackground = true)
@Composable
private fun PreViewSomeThing() {
    AndroidpaymentsTheme {
        BankSelectBottomSheet(
            onDismissRequest = {},
            onBankSelected = {},
        )
    }
}
