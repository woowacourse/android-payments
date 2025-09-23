package woowacourse.payments.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import woowacourse.payments.InputMask
import woowacourse.payments.R
import woowacourse.payments.list.CardUiModel
import woowacourse.payments.newCard.CardSelectionState
import woowacourse.payments.newCard.NewCardState

@Composable
fun CardInformationForm(
    newCardState: NewCardState,
    cardSelectionState: CardSelectionState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.height(14.dp))
        Box(
            modifier =
                Modifier
                    .fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {
            PaymentCard(
                state = PaymentCardState.CardInfo(
                    CardUiModel(
                        company = cardSelectionState.selectedCompany,
                        number = "",
                        name = null,
                        expiry = "",
                        password = "",
                    )
                )
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        DigitTextField(
            text = newCardState.cardNumber,
            onValueChange = { newCardState.onNumberChange(it) },
            label = stringResource(R.string.card_number_label),
            hint = "0000 - 0000 - 0000 - 0000",
            modifier = Modifier.padding(horizontal = 24.dp),
            maxLength = 16,
            mask = InputMask.CardNumber,
            errorMessage = newCardState.numberErrorMessage,
            imeAction = ImeAction.Next,
            isError = newCardState.isNumberError,
        )
        Spacer(modifier = Modifier.height(30.dp))
        DigitTextField(
            text = newCardState.cardExpiry,
            onValueChange = { newCardState.onExpiryChange(it) },
            label = stringResource(R.string.card_expiry_label),
            hint = "MM / YY",
            modifier =
                Modifier
                    .fillMaxWidth(0.5f)
                    .padding(horizontal = 24.dp),
            maxLength = 4,
            mask = InputMask.Expiry,
            errorMessage = newCardState.expiryErrorMessage,
            imeAction = ImeAction.Next,
            isError = newCardState.isExpiryError,
        )
        Spacer(modifier = Modifier.height(30.dp))
        LimitedUppercaseTextField(
            text = newCardState.cardName,
            onValueChange = { newCardState.onNameChange(it) },
            label = stringResource(R.string.card_owner_label),
            hint = stringResource(R.string.card_owner_hint),
            modifier = Modifier.padding(horizontal = 24.dp),
            maxLength = 30,
            imeAction = ImeAction.Next,
        )
        Spacer(modifier = Modifier.height(15.dp))
        DigitTextField(
            text = newCardState.cardPassword,
            onValueChange = { newCardState.onPasswordChange(it) },
            label = stringResource(R.string.card_password_label),
            hint = "0000",
            modifier =
                Modifier
                    .fillMaxWidth(0.5f)
                    .padding(horizontal = 24.dp),
            maxLength = 4,
            mask = InputMask.Password,
            errorMessage = newCardState.passwordErrorMessage,
            isError = newCardState.isPasswordError,
        )
    }
}