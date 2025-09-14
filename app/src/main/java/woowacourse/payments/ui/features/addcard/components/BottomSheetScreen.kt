package woowacourse.payments.ui.features.addcard.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.model.CardCompanyUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

private const val COLUMN_COUNT = 4

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
        FlowRow(
            modifier = Modifier.padding(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            maxItemsInEachRow = COLUMN_COUNT,
        ) {
            CardCompanyUiModel.entries.filter { it != CardCompanyUiModel.UNKNOWN }.forEach {
                Text(
                    text = it.companyName,
                    modifier = Modifier.clickable { onItemClick(it) },
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun BottomSheetScreenPreview() {
    val sheetState =
        rememberStandardBottomSheetState(
            initialValue = SheetValue.Expanded,
            confirmValueChange = { false },
        )

    AndroidpaymentsTheme(dynamicColor = false) {
        BottomSheetScreen(
            sheetState = sheetState,
            onDismiss = {},
            onItemClick = {},
        )
    }
}
