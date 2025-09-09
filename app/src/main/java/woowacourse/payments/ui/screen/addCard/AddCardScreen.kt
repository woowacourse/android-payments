package woowacourse.payments.ui.screen.addCard

import android.os.Bundle
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.CardOwner
import woowacourse.payments.domain.Expired
import woowacourse.payments.domain.Password
import woowacourse.payments.ui.AddCardUiState
import woowacourse.payments.ui.CardUiModel
import woowacourse.payments.ui.component.CardNumberInputField
import woowacourse.payments.ui.component.CardOwnerInputField
import woowacourse.payments.ui.component.ExpiredInputField
import woowacourse.payments.ui.component.NewCardTopBar
import woowacourse.payments.ui.component.PasswordInputField
import woowacourse.payments.ui.component.PaymentCard
import woowacourse.payments.ui.formatCardNumber
import woowacourse.payments.ui.formatExpired
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

@Composable
fun AddCardScreen(
    onBackPressed: () -> Unit,
    onCardSaved: (CardUiModel) -> Unit,
) {
    val addCardStateSaver =
        Saver<AddCardUiState, Bundle>(
            save = { state ->
                bundleOf(
                    "cardNumber" to state.cardNumber?.value,
                    "expired" to state.expired?.value,
                    "cardOwner" to state.cardOwner.value,
                    "password" to state.password?.value,
                    "showValidationError" to state.showValidationError,
                )
            },
            restore = { bundle ->
                AddCardUiState(
                    cardNumber = bundle.getString("cardNumber")?.let(::CardNumber),
                    expired = bundle.getString("expired")?.let(::Expired),
                    cardOwner = CardOwner(bundle.getString("cardOwner") ?: ""),
                    password = bundle.getString("password")?.let(::Password),
                    showValidationError = bundle.getBoolean("showValidationError"),
                )
            },
        )

    var uiState by rememberSaveable(stateSaver = addCardStateSaver) {
        mutableStateOf(AddCardUiState())
    }
    val scrollState = rememberScrollState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            NewCardTopBar(
                onBackClick = onBackPressed,
                onSaveClick = {
                    uiState = uiState.copy(showValidationError = !uiState.isFormValid)
                    if (uiState.isFormValid) {
                        val cardUiModel =
                            CardUiModel(
                                number = formatCardNumber(uiState.cardNumber),
                                expired = formatExpired(uiState.expired),
                                owner = uiState.cardOwner.value,
                            )
                        onCardSaved(cardUiModel)
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
                val cardUiModel =
                    CardUiModel(
                        number = formatCardNumber(uiState.cardNumber),
                        expired = formatExpired(uiState.expired),
                        owner = uiState.cardOwner.value,
                    )
                PaymentCard(card = cardUiModel)
            }

            CardNumberInputField(
                modifier = Modifier.fillMaxWidth(),
                cardNumber = uiState.cardNumber,
                onCardNumberChange = { uiState = uiState.copy(cardNumber = it) },
                showValidationError = uiState.showValidationError,
            )

            ExpiredInputField(
                modifier = Modifier.fillMaxWidth(0.5f),
                expired = uiState.expired,
                onExpiredChange = { uiState = uiState.copy(expired = it) },
                showValidationError = uiState.showValidationError,
            )

            CardOwnerInputField(
                modifier = Modifier.fillMaxWidth(),
                cardOwner = uiState.cardOwner,
                onOwnerChange = { uiState = uiState.copy(cardOwner = it) },
                showValidationError = uiState.showValidationError,
            )

            PasswordInputField(
                modifier = Modifier.fillMaxWidth(0.5f),
                password = uiState.password,
                onPasswordChange = { uiState = uiState.copy(password = it) },
                showValidationError = uiState.showValidationError,
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun AddCardScreenPreview() {
    AndroidpaymentsTheme {
        AddCardScreen(
            onBackPressed = {},
            onCardSaved = {},
        )
    }
}
