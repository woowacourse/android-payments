package woowacourse.payments.ui.newcard

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import woowacourse.payments.R
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.newcard.banks.BanksBottomSheet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewCardScreen(
    onSaveClick: (PaymentCardUiModel) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val stateHolder =
        rememberSaveable(saver = CreateCardStateHolderSaver()) { CreateCardStateHolder() }

    var showBottomSheet by remember { mutableStateOf(!stateHolder.hasBankType) }
    val context = LocalContext.current
    val modalBottomSheetState = rememberModalBottomSheetState()

    LaunchedEffect(showBottomSheet) {
        if (showBottomSheet) {
            showBottomSheet = true
        } else {
            modalBottomSheetState.hide()
        }
    }

    if (showBottomSheet) {
        BanksBottomSheet(
            sheetState = modalBottomSheetState,
            onSelectCard = { bank ->
                stateHolder.updateCardBank(bank)
                showBottomSheet = false
            },
            onDismissRequest = {
                handleDismiss(stateHolder, context)
                showBottomSheet = false
            }
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            NewCardTopBar(
                onBackClick = onBackClick,
                onSaveClick = { onSaveClick(stateHolder.newCard()) },
                isCreatable = stateHolder.isCardCreatable,
            )
        },
    ) { innerPadding ->
        NewCardContent(
            stateHolder.cardCreateState,
            { showBottomSheet = true },
            onCardNumbersChange = stateHolder::updateCardNumber,
            onCardExpiryDateChange = stateHolder::updateExpiryDate,
            onCardOwnerNameChange = stateHolder::updateOwnerName,
            onCardPasswordChange = stateHolder::updatePassword,
            modifier.padding(innerPadding),
        )
    }
}

private fun handleDismiss(stateHolder: CreateCardStateHolder, context: Context) {
    if (!stateHolder.hasBankType) {
        Toast.makeText(
            context,
            context.getString(R.string.not_select_bank_message),
            Toast.LENGTH_SHORT
        ).show()
    }
}
