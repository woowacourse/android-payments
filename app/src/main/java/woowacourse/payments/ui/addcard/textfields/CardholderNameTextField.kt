package woowacourse.payments.ui.addcard.textfields

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R
import woowacourse.payments.domain.CardholderName.Companion.CARDHOLDER_NAME_MAX_LENGTH
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.theme.Gray

@Composable
fun CardHolderNameTextField(card: MutableState<CardUiModel>) {
    LocalFocusManager.current

    fun updateValue(newValue: String) {
        card.value = card.value.copy(cardholderName = newValue.take(CARDHOLDER_NAME_MAX_LENGTH))
    }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = card.value.cardholderName,
        onValueChange = { newValue: String -> updateValue(newValue) },
        singleLine = true,
        label = { Text(stringResource(R.string.cardholder_name_label)) },
        placeholder = {
            Text(
                text = stringResource(R.string.cardholder_name_placeholder),
                color = Gray,
            )
        },
        supportingText = {
            Text(
                text =
                    stringResource(
                        R.string.cardholder_name_entry_length,
                        card.value.cardholderName.length,
                        CARDHOLDER_NAME_MAX_LENGTH,
                    ),
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth(),
            )
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
    )
}

@Preview(showBackground = true)
@Composable
fun CardHolderNameTextFieldPreview() {
    remember { mutableStateOf("디랙") }
    CardHolderNameTextField(
        remember {
            mutableStateOf(
                CardUiModel(
                    "1234123412341234",
                    "1234",
                    "CREW",
                    "0000",
                ),
            )
        },
    )
}
