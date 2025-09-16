package woowacourse.payments.ui.newcard

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import woowacourse.payments.domain.BankType
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.ui.CardStateHolder
import woowacourse.payments.ui.newcard.component.BankSelectRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BankSelectBottomSheet(
    state: CardStateHolder
) {
    val modalBottomSheetState = rememberModalBottomSheetState(
        confirmValueChange = { false }
    )
    var selectedBank by remember {
        mutableStateOf(BankType.NOT_SELECTED)
    }
    LaunchedEffect(key1 = selectedBank) {
        if (selectedBank != BankType.NOT_SELECTED) {
            modalBottomSheetState.hide()
        }
    }

    ModalBottomSheet(
        sheetState = modalBottomSheetState,
        onDismissRequest = { },
    ) {
        BankSelectRow(onClick = { state.changeBankType(it) })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun BankSelectBottomSheetPreview() {
    BankSelectBottomSheet(CardStateHolder())
}