package woowacourse.payments.newCard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardCompanySelectBottomSheet(
    modalBottomSheetState: SheetState,
    onClick: (CardCompanyUiModel) -> Unit,
    isShow: Boolean = true,
    onDismissRequest: () -> Unit,
    ) {
    if (isShow) {
        ModalBottomSheet(
            sheetState = modalBottomSheetState,
            onDismissRequest = { onDismissRequest() },
        ) {
            Box(
                modifier = Modifier.fillMaxWidth().padding(bottom = 70.dp),
                contentAlignment = Alignment.Center
            ) {
                CardCompanySelectRow(onClick)
            }
        }
    }
}