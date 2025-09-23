package woowacourse.payments.ui.newcard.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.ui.newcard.state.CardStateHolder
import woowacourse.payments.ui.model.CardCompanyUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectedCardCompanyBottomSheet(
    cardCompanyUiModel: CardCompanyUiModel = CardCompanyUiModel.Default,
    changeBottomSheet: () -> Unit,
    selectedCardCompany: (CardCompanyUiModel) -> Unit,
) {

    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )

    LaunchedEffect(key1 = cardCompanyUiModel) {
        if (cardCompanyUiModel != CardCompanyUiModel.Default) {
            bottomSheetState.hide()
            changeBottomSheet()
        } else {
            bottomSheetState.show()
        }
    }

    ModalBottomSheet(
        sheetState = bottomSheetState,
        onDismissRequest = { changeBottomSheet() },
    ) {
        SelectedBankRow(
            selectedBank = { selectedCard ->
                selectedCardCompany(selectedCard)
            },
        )
    }
}

@Preview
@Composable
private fun SelectedCardCompanyBottomSheetPreview() {
    SelectedCardCompanyBottomSheet(CardCompanyUiModel.Default, {}, {})
}