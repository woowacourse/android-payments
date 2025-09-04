package woowacourse.payments.ui

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
            password = newPassword.take(PASSWORD_LENGTH_MAX)
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

private const val PASSWORD_LENGTH_MAX: Int = 4
