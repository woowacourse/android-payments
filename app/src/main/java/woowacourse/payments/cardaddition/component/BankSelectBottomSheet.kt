package woowacourse.payments.cardaddition.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.BankType

private const val COLUMN_COUNT = 4

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BankSelectBottomSheet(
    onSelectBankType: (BankType) -> Unit,
    modifier: Modifier = Modifier,
    modalBottomSheetState: SheetState = rememberModalBottomSheetState(confirmValueChange = { sheetValue: SheetValue -> false }),
) {
    ModalBottomSheet(
        sheetState = modalBottomSheetState,
        onDismissRequest = {},
        modifier = modifier,
    ) {
        BankSelectRow(
            onSelectBank = onSelectBankType,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 43.dp, vertical = 36.dp),
        )
    }
}

@Composable
private fun BankSelectRow(
    onSelectBank: (BankType) -> Unit,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalArrangement = Arrangement.spacedBy(23.dp),
        maxItemsInEachRow = COLUMN_COUNT,
    ) {
        BankType.entries
            .forEach { bankType: BankType ->
                BankButton(
                    bankType = bankType,
                    onClick = onSelectBank,
                    modifier = Modifier.weight(1f),
                )
            }
    }
}

@Composable
private fun BankButton(
    bankType: BankType,
    onClick: (BankType) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .semantics { role = Role.Button }
                .clickable(onClick = { onClick(bankType) }),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(9.dp),
    ) {
        Image(
            painter = painterResource(bankType.imageRes),
            null,
            modifier = Modifier.size(37.dp),
        )
        Text(text = bankType.cardName, fontSize = 16.sp)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun BankSelectBottomSheetPreview() {
    val modalBottomSheetState =
        rememberModalBottomSheetState(confirmValueChange = { false })

    LaunchedEffect(Unit) { modalBottomSheetState.show() }

    BankSelectBottomSheet(
        onSelectBankType = {},
        modalBottomSheetState = modalBottomSheetState,
    )
}

@Preview(showBackground = true)
@Composable
private fun BankSelectRowPreview() {
    BankSelectRow(
        onSelectBank = {},
        modifier = Modifier.fillMaxHeight(),
    )
}

@Preview(showBackground = true)
@Composable
private fun BankButtonPreview() {
    BankButton(
        bankType = BankType.BC,
        onClick = {},
    )
}
