package woowacourse.payments.ui.newcard.textfields

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import woowacourse.payments.R
import woowacourse.payments.domain.Passcode

private const val PASSCODE_REQUIRED_LENGTH = 4

@Suppress("ktlint:standard:function-naming")
@Composable
fun PasscodeTextField(
    text: MutableState<String>,
    isError: MutableState<Boolean>,
) {
    val focusManager = LocalFocusManager.current

    CardInfoTextField(
        modifier = Modifier.fillMaxWidth(0.5F),
        value = text.value,
        label = stringResource(R.string.passcode_label),
        placeholder = stringResource(R.string.passcode_placeholder),
        isError = isError.value,
        supportingText = {
            Text(
                if (isError.value) {
                    stringResource(R.string.passcode_error_message)
                } else {
                    ""
                },
            )
        },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
    ) { newValue: String ->
        val filteredValue: String = newValue.filter(Char::isDigit).take(PASSCODE_REQUIRED_LENGTH)

        text.value = filteredValue
        isError.value = runCatching { Passcode(text.value) }.isFailure

        if (!isError.value && filteredValue.length == PASSCODE_REQUIRED_LENGTH) {
            focusManager.clearFocus()
        }
    }
}
