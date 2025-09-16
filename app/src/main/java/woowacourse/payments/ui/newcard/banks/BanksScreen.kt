package woowacourse.payments.ui.newcard.banks

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import woowacourse.payments.domain.BankType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BanksScreen(
    sheetState: SheetState,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onSelectCard: (BankType) -> Unit = {}
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        modifier = modifier
    ) {
        Banks(onSelectCard, modifier
            .fillMaxWidth()
            .padding(48.dp, 36.dp))
    }
}