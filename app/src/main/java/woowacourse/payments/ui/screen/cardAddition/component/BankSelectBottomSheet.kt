package woowacourse.payments.ui.screen.cardAddition.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.ui.model.IssuingBank

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BankSelectBottomSheet(
    sheetState: SheetState,
    onBankSelected: (IssuingBank) -> Unit,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {},
) {
    val context = LocalContext.current

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier =
            modifier.semantics {
                contentDescription =
                    context.getString(R.string.card_addition_issuing_bank_sheet_description)
            },
        sheetState = sheetState,
    ) {
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 36.dp),
            contentAlignment = Alignment.Center,
        ) {
            BankSelectRow(
                issuingBanks = IssuingBank.entries.filter { issuingBank -> issuingBank != IssuingBank.NOT_SELECTED },
                onBankSelect = { issuingBank -> onBankSelected(issuingBank) },
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun BankSelectBottomSheetPreview() {
    val sheetState = rememberModalBottomSheetState { false }
    BankSelectBottomSheet(
        sheetState = sheetState,
        onBankSelected = {},
        onDismissRequest = {},
    )
}
