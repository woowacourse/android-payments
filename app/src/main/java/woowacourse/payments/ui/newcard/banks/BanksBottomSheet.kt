package woowacourse.payments.ui.newcard.banks

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.domain.BankType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BanksBottomSheet(
    sheetState: SheetState,
    onDismissRequest: () -> Unit,
    onSelectCard: (BankType) -> Unit,
    modifier: Modifier = Modifier,
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        modifier = modifier,
    ) {
        Banks(
            onSelectCard,
            modifier
                .fillMaxWidth()
                .padding(48.dp, bottom = 106.dp, top = 36.dp),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun BanksBottomSheetPreview() {
    val sheetState = rememberModalBottomSheetState()
    LaunchedEffect(Unit) {
        sheetState.show()
    }
    BanksBottomSheet(sheetState, {}, {})
}
