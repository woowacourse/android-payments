package woowacourse.payments.ui.screen.cardAddition.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.model.IssuingBank

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BankSelectBottomSheet(
    onBankSelected: (IssuingBank) -> Unit,
    modifier: Modifier = Modifier,
) {
    val sheetState = rememberModalBottomSheetState(confirmValueChange = { false })
    var selectedBank by rememberSaveable { mutableStateOf(IssuingBank.NOT_SELECTED) }

    LaunchedEffect(selectedBank) {
        if (selectedBank != IssuingBank.NOT_SELECTED) {
            sheetState.hide()
        }
    }

    ModalBottomSheet(
        onDismissRequest = { onBankSelected(selectedBank) },
        modifier = modifier,
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
                onBankSelect = { issuingBank -> selectedBank = issuingBank },
            )
        }
    }
}

@Preview
@Composable
private fun BankSelectBottomSheetPreview() {
    BankSelectBottomSheet(
        onBankSelected = {},
    )
}
