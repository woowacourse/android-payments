package woowacourse.payments.ui.newcard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import woowacourse.payments.domain.BankType
import woowacourse.payments.ui.components.PaymentCard
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.newcard.banks.BanksScreen

private val ScreenAppBarSpacing = 14.dp
private val ScreenSectionSpacing = 40.dp
private val ScreenSidePadding = 24.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewCardScreen(
    onSaveClick: (PaymentCardUiModel) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val stateHolder =
        rememberSaveable(saver = CreateCardStateHolderSaver()) { CreateCardStateHolder() }

    val onSaveHandler = remember { { onSaveClick(stateHolder.newCard()) } }
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    val modalBottomSheetState = rememberModalBottomSheetState()

    LaunchedEffect(stateHolder.cardCreateState.bankType) {
        if (stateHolder.hasBankType) {
            modalBottomSheetState.hide()
        } else {
            modalBottomSheetState.show()
            showBottomSheet = true
        }
    }

    if (showBottomSheet) {
        BanksScreen(
            sheetState = modalBottomSheetState,
            onSelectCard = { bank ->
                scope.launch {
                    stateHolder.updateCardBank(bank)
                    showBottomSheet = false
                    modalBottomSheetState.hide()
                }
            },
            onDismissRequest = { showBottomSheet = false }
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            NewCardTopBar(
                onBackClick = onBackClick,
                onSaveClick = onSaveHandler,
                isCreatable = stateHolder.isCardCreatable
            )
        },
    ) { innerPadding ->
        Column(
            modifier =
                modifier.padding(innerPadding),
        ) {
            Spacer(modifier = Modifier.height(ScreenAppBarSpacing))
            PaymentCard(
                null,
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable {
                        stateHolder::updateCardBank
                    },
            )
            Spacer(modifier = Modifier.height(ScreenSectionSpacing))
            NewCardInputSection(
                newCardUiState = stateHolder.cardCreateState,
                onCardNumbersChange = stateHolder::updateCardNumber,
                onCardExpiryDateChange = stateHolder::updateExpiryDate,
                onCardOwnerNameChange = stateHolder::updateOwnerName,
                onCardPasswordChange = stateHolder::updatePassword,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = ScreenSidePadding),
            )
        }
    }
}

