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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import woowacourse.payments.R
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.newcard.components.NewCardContent
import woowacourse.payments.ui.newcard.NewCardStateHolder
import woowacourse.payments.ui.newcard.components.NewCardTopBar
import woowacourse.payments.ui.newcard.banks.BanksBottomSheet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateCardScreen(
    newCardStateHolder: NewCardStateHolder,
    currentCard: CardUiModel,
    onSaveClick: (CardUiModel) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val updateCardStateHolder = remember { UpdateCardStateHolder() }
    val isUpdatable by remember {
        derivedStateOf {
            newCardStateHolder.isCardCreatable && updateCardStateHolder.isCardUpdated(
                currentCard, newCardStateHolder.newCard(currentCard.id)
            )
        }
    }
    var showBottomSheet by remember { mutableStateOf(!newCardStateHolder.hasBankType) }
    val modalBottomSheetState = rememberModalBottomSheetState()

    LaunchedEffect(showBottomSheet) {
        if (showBottomSheet) {
            showBottomSheet = true
        } else {
            modalBottomSheetState.hide()
        }
    }

    if (showBottomSheet) {
        BanksBottomSheet(sheetState = modalBottomSheetState, onSelectCard = { bank ->
            newCardStateHolder.updateCardBank(bank)
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
                onSaveClick = { onSaveClick(newCardStateHolder.newCard(currentCard.id)) },
                title = stringResource(R.string.card_update),
                isCreatable = isUpdatable
            )
        },
    ) { innerPadding ->
        NewCardContent(
            newCardStateHolder.cardCreateState,
            { showBottomSheet = true },
            onCardNumbersChange = newCardStateHolder::updateCardNumber,
            onCardExpiryDateChange = newCardStateHolder::updateExpiryDate,
            onCardOwnerNameChange = newCardStateHolder::updateOwnerName,
            onCardPasswordChange = newCardStateHolder::updatePassword,
            modifier.padding(innerPadding),
        )
    }
}