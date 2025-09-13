package woowacourse.payments.ui.newcard

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.ui.model.CardHolderUiModel
import woowacourse.payments.ui.model.CardHolderUiModel.Companion.CARD_HOLDER_MAX_LENGTH
import woowacourse.payments.ui.model.CardNumberUiModel
import woowacourse.payments.ui.model.CardNumberUiModel.Companion.CARD_NUMBER_LENGTH
import woowacourse.payments.ui.model.ExpirationDateUiModel.Companion.EXPIRATION_DATE_LENGTH
import woowacourse.payments.ui.model.PasswordUiModel.Companion.PASSWORD_LENGTH
import woowacourse.payments.ui.model.PaymentCardUiModel
import woowacourse.payments.ui.newcard.components.CardNumberTextField
import woowacourse.payments.ui.newcard.components.ExpirationDateTextField
import woowacourse.payments.ui.newcard.components.NameTextField
import woowacourse.payments.ui.newcard.components.NewCardTopBar
import woowacourse.payments.ui.newcard.components.PasswordField
import woowacourse.payments.ui.newcard.components.PaymentCardBox

@Composable
fun NewCardScreen(
    newCardStateHolder: NewCardStateHolder = remember { NewCardStateHolder() },
    onBackPress: () -> Unit = {},
    onSaved: (PaymentCardUiModel) -> Unit = {},
) {
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            NewCardTopBar(
                onBackClick = { onBackPress() },
                onSaveClick = {
                    runCatching {
                        onSaved(
                            PaymentCardUiModel(
                                cardNumber = CardNumberUiModel(newCardStateHolder.cardNumber),
                                cardHolder = CardHolderUiModel(newCardStateHolder.cardHolder),
                                expirationDate = newCardStateHolder.expirationDateUiState.expirationDate,
                            ),
                        )
                    }.onFailure { e ->
                        Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                    }
                },
            )
        },
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
        ) {
            PaymentCardBox(
                modifier =
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 14.dp),
            )
            CardNumberTextField(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp, start = 24.dp, end = 24.dp),
                value = newCardStateHolder.cardNumber,
                onValueChange = { newCardStateHolder.cardNumber = it },
                maxLength = CARD_NUMBER_LENGTH,
            )
            ExpirationDateTextField(
                modifier =
                    Modifier
                        .padding(start = 24.dp, top = 30.dp),
                value = newCardStateHolder.expirationDateUiState.expirationDate.value,
                onValueChange = { newCardStateHolder.expirationDateUiState.onValueChanged(it) },
                isValid = newCardStateHolder.expirationDateUiState.isValid,
                maxLength = EXPIRATION_DATE_LENGTH,
            )
            NameTextField(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, top = 30.dp, end = 24.dp),
                value = newCardStateHolder.cardHolder,
                onValueChange = { newCardStateHolder.cardHolder = it },
                maxLength = CARD_HOLDER_MAX_LENGTH,
            )
            PasswordField(
                modifier =
                    Modifier
                        .padding(start = 24.dp, top = 30.dp),
                value = newCardStateHolder.password,
                onValueChange = { newCardStateHolder.password = it },
                maxLength = PASSWORD_LENGTH,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NewCardScreenPreview() {
    NewCardScreen()
}
