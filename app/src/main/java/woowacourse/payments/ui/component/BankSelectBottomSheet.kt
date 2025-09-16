package woowacourse.payments.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.domain.BankType
import woowacourse.payments.domain.BankType.NOT_SELECTED

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BankSelectBottomSheet(
    onBankSelected: (BankType) -> Unit,
    onDismiss: () -> Unit,
) {
    val modalBottomSheetState = rememberModalBottomSheetState(confirmValueChange = { false })

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = null,
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 28.dp, vertical = 48.dp),
        ) {
            FlowRow(
                modifier = Modifier.padding(4.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                BankType.entries.filter { it != NOT_SELECTED }.forEach { bank ->
                    BankItem(
                        bankType = bank,
                        onClick = { onBankSelected(bank) },
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BankSelectBottomSheetPreview() {
    BankSelectBottomSheet(
        onBankSelected = {},
        onDismiss = { },
    )
}
