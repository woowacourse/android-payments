package woowacourse.payments.ui.cards.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.BankViewType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectBankBottomSheet(
    onBankSelectClick: (BankViewType) -> Unit,
    onDismissRequest: () -> Unit,
    shape: Shape,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(),
    containerColor: Color = Color.White,
) {
    ModalBottomSheet(
        modifier = modifier,
        shape = shape,
        containerColor = containerColor,
        content = {
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(342.dp),
                contentAlignment = Alignment.Center,
                content = {
                    BankSelectRow(onBankClick = { bankType ->
                        onBankSelectClick(bankType)
                    })
                },
            )
        },
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun BottomSheetPreview() {
    SelectBankBottomSheet(
        modifier = Modifier.height(342.dp),
        shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
        onBankSelectClick = {},
        onDismissRequest = {},
    )
}
