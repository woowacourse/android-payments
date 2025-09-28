package woowacourse.payments.ui.newcard

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.model.toDomain
import woowacourse.payments.ui.model.toUiModel
import woowacourse.payments.ui.newcard.component.NewCardColumn
import woowacourse.payments.ui.newcard.component.NewCardTopBar
import woowacourse.payments.ui.newcard.component.SelectedCardCompanyBottomSheet
import woowacourse.payments.ui.newcard.state.CardStateHolder
import woowacourse.payments.ui.newcard.state.NewCardStatus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewCardScreen(
    newCardStatus: NewCardStatus,
    cardUiModel: CardUiModel?,
    navigateToBack: () -> Unit,
    onClickSaveCard: (CardUiModel?) -> Unit,
    onClickUpdateCard: (CardUiModel?, CardUiModel?) -> Unit,
    showToastMessage: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val stateHolder = remember { CardStateHolder() }
    LaunchedEffect(cardUiModel) {
        stateHolder.changeCard(cardUiModel?.toDomain())
    }
    var isBottomSheetOpen by rememberSaveable { mutableStateOf(true) }



    Scaffold(
        modifier = modifier,
        topBar = {
            NewCardTopBar(
                newCardStatus = newCardStatus,
                isPossibleAddCard = stateHolder.uiState.value.isPossibleAddCard,
                onBackClick = { navigateToBack() },
                onSaveClick = {
                    val created = stateHolder.newCard()
                    when (newCardStatus) {
                        is NewCardStatus.CreateCard -> onClickSaveCard(created?.toUiModel())
                        is NewCardStatus.EditCard -> {
                            if (cardUiModel == created?.toUiModel()) showToastMessage("동일한 카드 입니다")
                            else onClickUpdateCard(cardUiModel, created?.toUiModel())
                        }
                    }
                }
            )
        }) { paddingValues: PaddingValues ->
        if (isBottomSheetOpen) {
            SelectedCardCompanyBottomSheet(
                changeBottomSheet = { isBottomSheetOpen = !isBottomSheetOpen },
                selectedCardCompany = { cardCompanyUiModel ->
                    stateHolder.selectedCardCompany(
                        cardCompanyUiModel
                    )
                }
            )
        }
        NewCardColumn(
            uiState = stateHolder.uiState.value,
            selectCardCompany = { isBottomSheetOpen = !isBottomSheetOpen },
            changeNumber = { stateHolder.changeNumber(it) },
            changeExpiredDate = { stateHolder.changeExpiredDate(it) },
            changeOwnerName = { stateHolder.changeOwnerName(it) },
            changePassword = { stateHolder.changePassword(it) },
            modifier = Modifier.padding(paddingValues),
        )
    }
}

@Preview
@Composable
private fun NewCardScreenPreview1() {
    NewCardScreen(NewCardStatus.CreateCard, null, {}, {}, { _, _ -> }, {})
}
