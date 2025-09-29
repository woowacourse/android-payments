package woowacourse.payments.ui.newcard.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R
import woowacourse.payments.domain.Password

@Composable
fun PasswordTextField(
    password: String,
    onPasswordChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var isFocused by remember { mutableStateOf(false) }
    val currentError = remember(password, isFocused) {
        if (isFocused || password.isEmpty()) {
            null
        } else {
            runCatching {
                val trimmed = password.take(4)
                Password(trimmed)
                null
            }.getOrElse { it.message }
        }
    }

    OutlinedTextField(
        value = password,
        onValueChange = { newValue: String ->
            onPasswordChange(
                newValue.filter(Char::isDigit)
                    .take(newValue.length.coerceAtMost(PASSWORD_LENGTH_MAX))
            )
        },
        modifier = modifier.onFocusChanged{ state ->
            isFocused = state.isFocused
        },
        label = {
            Text(text = stringResource(R.string.password_label))
        },
        placeholder = {
            Text(text = stringResource(R.string.password_placeholder), color = Color.Gray)
        },
        isError = !isFocused && currentError != null,
        supportingText = {
            if (!isFocused && currentError != null) {
                Text(
                    text = currentError,
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        visualTransformation = PasswordVisualTransformation(),
    )
}

@Preview
@Composable
private fun PasswordTextFieldPreview() {
    var password by remember { mutableStateOf("") }

    PasswordTextField(
        password = password,
        onPasswordChange = { password = it },
    )
}

private const val PASSWORD_LENGTH_MAX: Int = 4