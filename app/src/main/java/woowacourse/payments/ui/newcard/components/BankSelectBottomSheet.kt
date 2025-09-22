package woowacourse.payments.ui.newcard.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.payments.designsystem.theme.GrayLabel
import woowacourse.payments.domain.model.BankType
import woowacourse.payments.ui.common.mapper.toIconRes
import woowacourse.payments.ui.common.mapper.toNameRes

private const val COLUMN_COUNT: Int = 4

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BankSelectBottomSheet(
    isOpen: Boolean = false,
    selected: BankType,
    onDismiss: () -> Unit,
    onSelected: (BankType) -> Unit,
) {
    if (!isOpen) return
    val sheet =
        rememberModalBottomSheetState(
            skipPartiallyExpanded = true,
            confirmValueChange = { false },
        )
    var localSelected by remember { mutableStateOf(selected) }

    LaunchedEffect(localSelected) {
        if (localSelected != BankType.NOT_SELECTED) {
            sheet.hide()
            onDismiss()
        }
    }

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = sheet,
        containerColor = White,
    ) {
        FlowRow(
            modifier =
                Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            maxItemsInEachRow = COLUMN_COUNT,
        ) {
            BankType.entries
                .filter { it != BankType.NOT_SELECTED }
                .forEach { bank ->
                    BankChip(
                        bank = bank,
                        onClick = {
                            localSelected = bank
                            onSelected(bank)
                        },
                        modifier = Modifier.weight(1f),
                    )
                }
        }
        Spacer(Modifier.height(24.dp))
    }
}

@Composable
private fun BankChip(
    bank: BankType,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val label = stringResource(bank.toNameRes())
        Image(painterResource(bank.toIconRes()), contentDescription = label, modifier = Modifier.size(36.dp))
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = label, color = GrayLabel, fontSize = 16.sp, fontWeight = FontWeight.W500)
    }
}
