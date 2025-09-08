package woowacourse.payments.ui.screen.addCard

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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.CardOwner
import woowacourse.payments.domain.Expired
import woowacourse.payments.domain.Password
import woowacourse.payments.ui.CardUiModel
import woowacourse.payments.ui.component.CardNumberInputField
import woowacourse.payments.ui.component.CardOwnerInputField
import woowacourse.payments.ui.component.ExpiredInputField
import woowacourse.payments.ui.component.NewCardTopBar
import woowacourse.payments.ui.component.PasswordInputField
import woowacourse.payments.ui.component.PaymentCard
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.toPresentation

@Composable
fun AddCardScreen(
    onBackPressed: () -> Unit,
    onCardSaved: (CardUiModel) -> Unit,
) {
    val cardNumberSaver =
        Saver<CardNumber?, String>(
            save = { it?.value },
            restore = { CardNumber(it) },
        )

    val expiredSaver =
        Saver<Expired?, String>(
            save = { it?.value },
            restore = { Expired(it) },
        )

    val cardOwnerSaver =
        Saver<CardOwner?, String>(
            save = { it?.value },
            restore = { CardOwner(it) },
        )

    val passwordSaver =
        Saver<Password?, String>(
            save = { it?.value },
            restore = { Password(it) },
        )

    var cardNumber by rememberSaveable(stateSaver = cardNumberSaver) { mutableStateOf(null) }
    var expired by rememberSaveable(stateSaver = expiredSaver) { mutableStateOf(null) }
    var cardOwner by rememberSaveable(stateSaver = cardOwnerSaver) { mutableStateOf(CardOwner("")) }
    var password by rememberSaveable(stateSaver = passwordSaver) { mutableStateOf(null) }
    var showValidationError by rememberSaveable { mutableStateOf(false) }

    val scrollState = rememberScrollState()

    val isFormValid by remember(cardNumber, expired, cardOwner, password) {
        derivedStateOf {
            (cardNumber?.isValid == true) &&
                (expired?.isValid == true) &&
                (cardOwner?.isValid != false) &&
                (password?.isValid == true)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            NewCardTopBar(
                onBackClick = onBackPressed,
                onSaveClick = {
                    showValidationError = !isFormValid
                    if (isFormValid) {
                        val cardUiModel =
                            Card(
                                number = cardNumber,
                                expired = expired,
                                owner = cardOwner,
                                password = password,
                            ).toPresentation()
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
                PaymentCard(
                    Card(
                        number = cardNumber,
                        expired = expired,
                        owner = cardOwner,
                        password = password,
                    ).toPresentation(),
                )
            }

            CardNumberInputField(
                cardNumber = cardNumber,
                onCardNumberChange = { cardNumber = it },
                modifier = Modifier.fillMaxWidth(),
                showValidationError = showValidationError,
            )

            ExpiredInputField(
                expired = expired,
                onExpiredChange = { expired = it },
                modifier = Modifier.fillMaxWidth(0.5f),
                showValidationError = showValidationError,
            )

            CardOwnerInputField(
                cardOwner = cardOwner,
                onOwnerChange = { cardOwner = it },
                modifier = Modifier.fillMaxWidth(),
                showValidationError = showValidationError,
            )

            PasswordInputField(
                password = password,
                onPasswordChange = { password = it },
                modifier = Modifier.fillMaxWidth(0.5f),
                showValidationError = showValidationError,
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
