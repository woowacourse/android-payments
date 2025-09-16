package woowacourse.payments.ui.card.register.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import woowacourse.payments.domain.Bank

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BankSelectBottomSheet(
    banks: List<Bank>,
    onBankSelected: (Bank) -> Unit,
) {
    val modalBottomSheetState =
        rememberModalBottomSheetState(
            confirmValueChange = { false },
        )

    ModalBottomSheet(
        sheetState = modalBottomSheetState,
        onDismissRequest = {},
    ) {
        Column(
            modifier = Modifier.padding(vertical = 36.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            BankSelectRow(
                banks = banks,
                onBankSelected = onBankSelected,
            )
        }
    }
}
