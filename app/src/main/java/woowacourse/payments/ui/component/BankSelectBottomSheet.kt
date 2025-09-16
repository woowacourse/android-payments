package woowacourse.payments.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.domain.model.BankType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BankSelectBottomSheet(onSelect: (BankType) -> Unit) {
    val sheetState =
        rememberModalBottomSheetState(
            skipPartiallyExpanded = true,
            confirmValueChange = { false },
        )

    var selectedBank by remember {
        mutableStateOf(BankType.NOT_SELECTED)
    }

    LaunchedEffect(selectedBank) {
        if (selectedBank != BankType.NOT_SELECTED) sheetState.hide()
    }

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = {},
        containerColor = Color.White,
    ) {
        BankSelectRow(
            onSelect = {
                selectedBank = it
                onSelect(it)
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
    )
}
