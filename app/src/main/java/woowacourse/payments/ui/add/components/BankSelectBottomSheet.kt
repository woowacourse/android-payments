package woowacourse.payments.ui.add.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.domain.model.BankType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BankSelectBottomSheet(
    onSelect: (BankType) -> Unit,
    onDismiss: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDismiss,
        containerColor = Color.White,
        properties = ModalBottomSheetProperties(shouldDismissOnBackPress = true),
    ) {
        BankSelectRow(
            onSelect = {
                onSelect(it)
                onDismiss()
            },
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(top = 19.dp, bottom = 70.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BankSelectBottomSheetPreview() {
    BankSelectBottomSheet(
        onSelect = {},
        onDismiss = {},
    )
}
