package woowacourse.payments.ui.features.addcard.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.model.CardCompanyUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetScreen(
    sheetState: SheetState,
    onDismiss: () -> Unit,
    onItemClick: (CardCompanyUiModel) -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "BC카드(테스트)",
                modifier = Modifier.clickable { onItemClick(CardCompanyUiModel.BC) },
            )
            Text(
                text = "신한카드(테스트)",
                modifier = Modifier.clickable { onItemClick(CardCompanyUiModel.SHINHAN) },
            )
        }
    }
}
