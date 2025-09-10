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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.domain.Passcode
import woowacourse.payments.domain.Passcode.Companion.PASSCODE_REQUIRED_LENGTH
import woowacourse.payments.ui.model.CardUiModel
import woowacourse.payments.ui.theme.Gray

@Composable
fun PasscodeTextField(
    card: MutableState<CardUiModel>,
    isError: MutableState<Boolean>,
) {
    val focusManager = LocalFocusManager.current

    fun updateValue(newValue: String) {
        val filteredValue: String =
            newValue.filter(Char::isDigit).take(PASSCODE_REQUIRED_LENGTH)

        card.value = card.value.copy(passcode = filteredValue)
        isError.value = runCatching { Passcode(card.value.passcode) }.isFailure

        if (!isError.value && filteredValue.length == PASSCODE_REQUIRED_LENGTH) {
            focusManager.clearFocus()
        }
    }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(0.5F),
        value = card.value.passcode,
        onValueChange = { newValue: String -> updateValue(newValue) },
        singleLine = true,
        visualTransformation = PasswordVisualTransformation(),
        label = { Text(stringResource(R.string.passcode_label)) },
        placeholder = {
            Text(
                text = stringResource(R.string.passcode_placeholder),
                color = Gray,
            )
        },
        supportingText = {
            Box(Modifier.height(20.dp)) {
                if (isError.value) Text(stringResource(R.string.passcode_error_message)) else null
            }
        },
        isError = isError.value,
        keyboardOptions =
            KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Done,
            ),
    )
}

@Preview(showBackground = true)
@Composable
fun PasscodeTextFieldPreview() {
    PasscodeTextField(
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
fun PasscodeTextFieldWithErrorPreview() {
    PasscodeTextField(
        card =
            remember {
                mutableStateOf(
                    CardUiModel(
                        "1234123412341234",
                        "1234",
                        "CREW",
                        "00",
                    ),
                )
            },
        isError = remember { mutableStateOf(true) },
    )
}
