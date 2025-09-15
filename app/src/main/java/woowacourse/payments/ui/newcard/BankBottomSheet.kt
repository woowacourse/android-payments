package woowacourse.payments.ui.newcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.data.BankRepository
import woowacourse.payments.domain.BankType
import woowacourse.payments.ui.model.Bank
import woowacourse.payments.ui.newcard.components.BankItem
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BankBottomSheet(
    sheetState: SheetState,
    banks: List<Bank>,
    onClick: (Bank) -> Unit,
    modifier: Modifier = Modifier,
) {
    var selectedBank by remember {
        mutableStateOf(BankType.NOT_SELECTED)
    }

    LaunchedEffect(key1 = selectedBank) {
        if (selectedBank != BankType.NOT_SELECTED) {
            sheetState.hide()
        }
    }

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = { },
        modifier = modifier,
    ) {
        Spacer(modifier = Modifier.size(92.dp))
        FlowRow(
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalArrangement = Arrangement.spacedBy(24.dp),
            maxItemsInEachRow = 4,
        ) {
            banks.forEach { bank ->
                BankItem(
                    bank = bank,
                    onClick = {
                        onClick(bank)
                        selectedBank = bank.type
                    },
                )
            }
        }
        Spacer(modifier = Modifier.size(106.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun BankBottomSheetPreview() {
    AndroidpaymentsTheme {
        BankBottomSheet(
            sheetState = rememberStandardBottomSheetState(),
            banks = BankRepository.getCompanies(),
            onClick = {},
        )
    }
}
