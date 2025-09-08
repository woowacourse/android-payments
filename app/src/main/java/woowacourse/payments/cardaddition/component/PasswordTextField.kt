package woowacourse.payments.cardaddition.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R
import java.lang.Character.isDigit

@Composable
fun PasswordTextField(modifier: Modifier = Modifier) {
    var password: String by remember { mutableStateOf("") }

    OutlinedTextField(
        value = password,
        onValueChange = { newValue: String ->
            val newPassword: String = newValue.filter(::isDigit)
            password = newPassword.take(PASSWORD_LENGTH)
        },
        modifier = modifier,
        label = {
            Text(text = stringResource(R.string.password_label))
        },
        placeholder = {
            Text(
                text = stringResource(R.string.password_placeholder),
                color = Color.Gray,
            )
        },
        supportingText = {
            if (password.isInvalidPassword) {
                Text(
                    text = stringResource(R.string.text_field_invalid_format_message),
                    color = Color.Red,
                )
            }
        },
        isError = password.isInvalidPassword,
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions =
            KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Done,
            ),
    )
}

@Preview
@Composable
private fun PasswordTextFieldPreview() {
    PasswordTextField()
}

private val String.isInvalidPassword: Boolean get() = isNotEmpty() && length != PASSWORD_LENGTH

private const val PASSWORD_LENGTH: Int = 4
