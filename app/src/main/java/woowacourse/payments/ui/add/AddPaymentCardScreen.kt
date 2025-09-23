package woowacourse.payments.ui.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.domain.PaymentCardStore
import woowacourse.payments.ui.component.BankSelectBottomSheet
import woowacourse.payments.ui.component.CardNumberTextField
import woowacourse.payments.ui.component.ExpiryTextField
import woowacourse.payments.ui.component.NewCardTopBar
import woowacourse.payments.ui.component.PaymentCard
import woowacourse.payments.ui.component.PinTextField
import woowacourse.payments.ui.component.StringTextField
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.model.mapper.toUiModelOrPlaceholder
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun AddPaymentCardScreen(
    onBack: () -> Unit,
    onSave: () -> Unit,
    cardId: String? = null,
    stateHolder: AddPaymentCardStateHolder = rememberAddPaymentCardStateHolder(),
) {
    LaunchedEffect(cardId) {
        if (cardId != null) PaymentCardStore.findById(cardId)?.let { stateHolder.beginEdit(it) }
    }

    val state = stateHolder.state

    val canSave by remember(state.cardNumber, state.expiry, state.pin) {
        derivedStateOf {
            stateHolder.isCardNumberValid &&
                stateHolder.isExpiryValid &&
                stateHolder.isPinValid
        }
    }

    val previewCard =
        remember(state.cardNumber, state.expiry, state.owner, state.bank) {
            PaymentCardUiModel(
                id = cardId ?: "",
                cardNumber = state.cardNumber,
                expiry = state.expiry,
                owner = state.owner,
                bank = state.bank.toUiModelOrPlaceholder(),
            )
        }

    Scaffold(
        topBar = {
            NewCardTopBar(
                modifier = Modifier.padding(bottom = 14.dp),
                onBackClick = onBack,
                onSaveClick = {
                    if (!stateHolder.isBankValid) {
                        stateHolder.showSheet()
                    } else {
                        stateHolder.buildResult()?.let { card ->
                            if (state.isEditing) {
                                PaymentCardStore.update(card)
                            } else {
                                PaymentCardStore.add(card)
                            }
                            onSave()
                        }
                    }
                },
                saveEnabled = canSave,
            )
        },
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
        ) {
            PaymentCard(
                paymentCard = previewCard,
                onSelectBank = { stateHolder.showSheet() },
                onEditCard = {},
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )

            Spacer(modifier = Modifier.height(40.dp))

            CardNumberTextField(
                value = state.cardNumber,
                onValueChange = stateHolder::onCardNumberChange,
                modifier = Modifier.fillMaxWidth(),
            )

            ExpiryTextField(
                value = state.expiry,
                onValueChange = stateHolder::onExpiryChange,
                modifier = Modifier.fillMaxWidth(0.6f),
            )

            StringTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.owner,
                onValueChange = stateHolder::onOwnerChange,
                maxLength = 30,
            )

            PinTextField(
                value = state.pin,
                onValueChange = stateHolder::onPinChange,
                modifier = Modifier.fillMaxWidth(0.6f),
            )
        }
    }

    if (state.isSheetVisible) {
        BankSelectBottomSheet(
            onSelect = { bank ->
                stateHolder.onBankChange(bank)
            },
            onDismiss = { stateHolder.hideSheet() },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AddPaymentCardScreenPreview() {
    AndroidpaymentsTheme {
        AddPaymentCardScreen(
            onBack = {},
            onSave = {},
        )
    }
}
