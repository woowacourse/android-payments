package woowacourse.payments.ui.newcard.update

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import woowacourse.payments.R
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.newcard.banks.BanksBottomSheet
import woowacourse.payments.ui.newcard.components.NewCardContent
import woowacourse.payments.ui.newcard.components.NewCardTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateCardScreen(
    updateCardStateHolder: UpdateCardStateHolder,
    cardId: Long,
    onSaveClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val updateCardStateHolder =
        rememberSaveable(saver = UpdateCardStateHolderSaver()) {
            updateCardStateHolder
        }
    val isUpdatable by remember {
        derivedStateOf {
            updateCardStateHolder.isCardUpdatable(
                cardId
            )
        }
    }
    var showBottomSheet by remember { mutableStateOf(false) }
    val modalBottomSheetState = rememberModalBottomSheetState()

    LaunchedEffect(Unit) {
        updateCardStateHolder.updateCardInfo(cardId)
    }

    LaunchedEffect(showBottomSheet) {
        if (showBottomSheet) {
            showBottomSheet = true
        } else {
            modalBottomSheetState.hide()
        }
    }

    if (showBottomSheet) {
        BanksBottomSheet(
            banks = updateCardStateHolder.selectableCardBanks(),
            sheetState = modalBottomSheetState, onSelectCard = { bank ->
                updateCardStateHolder.updateCardBank(bank)
                showBottomSheet = false
            }, onDismissRequest = {
                showBottomSheet = false
            })
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            NewCardTopBar(
                onBackClick = onBackClick,
                onSaveClick = {
                    updateCardStateHolder.updateCard(cardId)
                    onSaveClick()
                },
                title = stringResource(R.string.card_update),
                isCreatable = isUpdatable
            )
        },
    ) { innerPadding ->
        NewCardContent(
            updateCardStateHolder.uiState.newCardContentUiState,
            { showBottomSheet = true },
            onCardNumbersChange = updateCardStateHolder::updateCardNumber,
            onCardExpiryDateChange = updateCardStateHolder::updateExpiryDate,
            onCardOwnerNameChange = updateCardStateHolder::updateOwnerName,
            onCardPasswordChange = updateCardStateHolder::updatePassword,
            modifier.padding(innerPadding),
        )
    }
}