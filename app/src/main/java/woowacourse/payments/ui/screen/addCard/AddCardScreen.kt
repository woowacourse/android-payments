package woowacourse.payments.ui.screen.addCard

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import woowacourse.payments.R
import woowacourse.payments.ui.component.BankSelectBottomSheet
import woowacourse.payments.ui.component.CardNumberInputField
import woowacourse.payments.ui.component.CardOwnerInputField
import woowacourse.payments.ui.component.ExpiredInputField
import woowacourse.payments.ui.component.NewCardTopBar
import woowacourse.payments.ui.component.PasswordInputField
import woowacourse.payments.ui.component.PaymentCard
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCardScreen(
    stateHolder: AddCardStateHolder,
    onBackPressed: () -> Unit,
    onCardSaved: (CardUiModel) -> Unit,
) {
    val uiState = stateHolder.uiState
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    var showBottomSheet by remember { mutableStateOf(true) }
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            NewCardTopBar(
                title =
                    if (stateHolder.isEditMode) {
                        stringResource(R.string.add_card_modify_title)
                    } else {
                        stringResource(
                            R.string.add_card_add_title,
                        )
                    },
                onBackClick = onBackPressed,
                onSaveClick = {
                    if (!stateHolder.hasChanges()) {
                        Toast
                            .makeText(
                                context,
                                R.string.add_card_no_modify_toast,
                                Toast.LENGTH_SHORT,
                            ).show()
                        return@NewCardTopBar
                    }
                    if (stateHolder.validate()) {
                        onCardSaved(uiState.toCardUiModel())
                    }
                },
            )
        },
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp)
                    .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                contentAlignment = Alignment.Center,
            ) {
                PaymentCard(
                    card = stateHolder.uiState.toCardUiModel(),
                    modifier = Modifier.clickable { showBottomSheet = true },
                )
            }

            CardNumberInputField(
                modifier = Modifier.fillMaxWidth(),
                cardNumber = uiState.cardNumber,
                onCardNumberChange = { stateHolder.updateCardNumber(it) },
                error = uiState.cardNumberError,
            )

            ExpiredInputField(
                modifier = Modifier.fillMaxWidth(0.5f),
                expired = uiState.expired,
                onExpiredChange = { stateHolder.updateExpired(it) },
                error = uiState.expiredError,
            )

            CardOwnerInputField(
                modifier = Modifier.fillMaxWidth(),
                cardOwner = uiState.cardOwner,
                onOwnerChange = { stateHolder.updateCardOwner(it) },
                error = uiState.ownerError,
            )

            PasswordInputField(
                modifier = Modifier.fillMaxWidth(0.5f),
                password = uiState.password,
                onPasswordChange = { stateHolder.updatePassword(it) },
                error = uiState.passwordError,
            )
        }
    }

    if (showBottomSheet) {
        BankSelectBottomSheet(
            sheetState = bottomSheetState,
            banks = stateHolder.allBanks,
            onBankSelected = { bank ->
                stateHolder.updateBank(bank)
                coroutineScope.launch { bottomSheetState.hide() }.invokeOnCompletion {
                    showBottomSheet = false
                }
            },
            onDismiss = {
                showBottomSheet = false
            },
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun AddCardScreenPreview() {
    AndroidpaymentsTheme {
        val preViewStateHolder =
            rememberSaveable(saver = AddCardStateHolder.saver) { AddCardStateHolder() }

        AddCardScreen(
            stateHolder = preViewStateHolder,
            onBackPressed = {},
            onCardSaved = {},
        )
    }
}
