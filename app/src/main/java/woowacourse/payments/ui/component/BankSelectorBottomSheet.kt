package woowacourse.payments.ui.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R
import woowacourse.payments.ui.extension.semanticsContentDescription
import woowacourse.payments.ui.model.BankTypeUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BankSelectBottomSheet(
    onBankSelected: (BankTypeUiModel) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val sheetState = rememberModalBottomSheetState(confirmValueChange = { false })
    var selectedBank by remember { mutableStateOf(BankTypeUiModel.NOT_SELECTED) }

    LaunchedEffect(selectedBank) {
        if (selectedBank != BankTypeUiModel.NOT_SELECTED) {
            sheetState.hide()
            onDismiss()
        }
    }

    ModalBottomSheet(
        modifier = modifier.semanticsContentDescription(R.string.issuing_bank_selector_bottom_sheet_content_description),
        sheetState = sheetState,
        onDismissRequest = {},
        containerColor = Color.White,
    ) {
        BankSelectRow(
            onBankSelected = { bankType ->
                selectedBank = bankType
                onBankSelected(bankType)
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BankSelectBottomSheetPreview() {
    BankSelectBottomSheet(
        onBankSelected = { },
        onDismiss = { },
    )
}
