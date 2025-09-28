package woowacourse.payments.ui.newcard.banks

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import woowacourse.payments.ui.model.BankUiModel
import woowacourse.payments.ui.model.toLocalBankUiModel

private const val BANK_COLUMN_COUNT = 4

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BanksBottomSheet(
    banks: List<BankUiModel>,
    sheetState: SheetState,
    onDismissRequest: () -> Unit,
    onSelectCard: (BankUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        modifier = modifier,
    ) {
        BanksGrid(
            banks,
            columnCount = BANK_COLUMN_COUNT,
            Modifier
                .height(250.dp)
                .padding(horizontal = 20.dp),
        ) { bank ->
            Bank(
                bankUiModel = bank,
                onSelectBank = onSelectCard,
                modifier =
                    Modifier
                        .weight(1f, fill = true)
                        .fillMaxWidth(),
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(widthDp = 360, heightDp = 640)
@Composable
fun BanksBottomSheetPreview() {
    val sheetState = rememberModalBottomSheetState()
    LaunchedEffect(Unit) {
        sheetState.show()
    }
    BanksBottomSheet(banks = BankType.entries.map { it.toLocalBankUiModel() }, sheetState, {}, {})
}
