package woowacourse.payments.ui.addcard

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.domain.BankType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BankSelectBottomSheet(
    onDismiss: () -> Unit,
    onBankSelected: (BankType) -> Unit,
) {
    val modalBottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        sheetState = modalBottomSheetState,
        onDismissRequest = onDismiss,
    ) {
        BankSelectRow(onClick = onBankSelected)
    }
}

@Preview(showBackground = true)
@Composable
private fun BankSelectBottomSheetPreview() {
    BankSelectBottomSheet(
        onDismiss = {},
        onBankSelected = {},
    )
}
