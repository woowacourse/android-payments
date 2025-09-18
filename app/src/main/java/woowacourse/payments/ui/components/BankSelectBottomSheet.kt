package woowacourse.payments.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.domain.model.CardCompanyType
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BankSelectBottomSheet(
    visible: Boolean,
    onDismissRequest: () -> Unit,
    onSelect: (CardCompanyType) -> Unit,
    blockUserDismiss: Boolean = false,
) {
    val sheetState =
        rememberModalBottomSheetState(
            skipPartiallyExpanded = true,
            confirmValueChange = { newValue ->
                if (blockUserDismiss) newValue != SheetValue.Hidden else true
            },
        )

    LaunchedEffect(visible) {
        if (visible) {
            sheetState.expand()
        } else {
            sheetState.hide()
        }
    }

    if (visible) {
        ModalBottomSheet(
            containerColor = Color.White,
            sheetState = sheetState,
            onDismissRequest = onDismissRequest,
        ) {
            CardCompanySelectRow(
                onSelect = { type ->
                    onSelect(type)
                    onDismissRequest()
                },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BankSelectBottomSheetPreview() {
    AndroidpaymentsTheme {
        BankSelectBottomSheet(
            visible = true,
            onDismissRequest = {},
            onSelect = {},
        )
    }
}
