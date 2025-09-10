package woowacourse.payments.ui.addcard.textfields

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.ui.format.CardNumberFormat
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.theme.Gray

@Composable
fun CardNumberTextField(
    card: MutableState<CardUiModel>,
    isError: MutableState<Boolean>,
) {
    val focusManager = LocalFocusManager.current

    fun updateValue(newValue: String) {
        val filteredValue: String =
            newValue.filter(Char::isDigit).take(CardNumberFormat.REQUIRED_LENGTH)

        card.value = card.value.copy(cardNumber = filteredValue)
        isError.value = runCatching { CardNumber(card.value.cardNumber) }.isFailure

        if (!isError.value && filteredValue.length == CardNumberFormat.REQUIRED_LENGTH) {
            focusManager.moveFocus(FocusDirection.Next)
        }
    }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = card.value.cardNumber,
        onValueChange = { newValue: String -> updateValue(newValue) },
        singleLine = true,
        visualTransformation = CardNumberFormat.visualTransformation,
        label = { Text(stringResource(R.string.card_number_label)) },
        placeholder = {
            Text(
                text = stringResource(R.string.card_number_placeholder),
                color = Gray,
            )
        },
        supportingText = {
            Box(Modifier.height(20.dp)) {
                if (isError.value) Text(stringResource(R.string.card_number_error_message)) else null
            }
        },
        isError = isError.value,
        keyboardOptions =
            KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next,
            ),
    )
}

@Preview(showBackground = true)
@Composable
fun CardNumberTextFieldPreview() {
    CardNumberTextField(
        card =
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
        isError = remember { mutableStateOf(false) },
    )
}

@Preview(showBackground = true)
@Composable
fun CardNumberTextFieldWithErrorPreview() {
    CardNumberTextField(
        card =
            remember {
                mutableStateOf(
                    CardUiModel(
                        "12341234",
                        "1234",
                        "CREW",
                        "0000",
                    ),
                )
            },
        isError = remember { mutableStateOf(true) },
    )
}
