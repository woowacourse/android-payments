package woowacourse.payments.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.theme.Grey40

@Composable
fun PasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        keyboardOptions =
            KeyboardOptions(
                keyboardType = KeyboardType.Number,
            ),
        modifier = modifier,
        value = value,
        visualTransformation = PasswordVisualTransformation(),
        onValueChange = { input ->
            val formatted = input.filter(Char::isDigit).take(PASSWORD_MAX_LENGTH)
            onValueChange(formatted)
        },
        label = { Text(stringResource(R.string.password_label)) },
        placeholder = {
            Text(
                text = stringResource(R.string.password_placeholder),
                color = Grey40,
            )
        },
        singleLine = true,
    )
}

@Preview(showBackground = true)
@Composable
private fun PasswordFieldPreview() {
    AndroidpaymentsTheme {
        PasswordField(
            value = "1234",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(0.5f),
        )
    }
}

private const val PASSWORD_MAX_LENGTH = 4
