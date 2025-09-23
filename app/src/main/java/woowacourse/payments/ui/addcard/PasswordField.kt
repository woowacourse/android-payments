package woowacourse.payments.ui.addcard

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
import woowacourse.payments.domain.Password

@Composable
fun PasswordField(
    password: Password,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit = {},
) {
    OutlinedTextField(
        value = password.toUiModel().toString(),
        onValueChange = onValueChange,
        modifier = modifier,
        placeholder = { Text(stringResource(R.string.add_card_password_placeholder_text)) },
        label = { Text(stringResource(R.string.add_card_password_label_text)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = PasswordVisualTransformation(),
        singleLine = true,
    )
}

@Preview(showBackground = true)
@Composable
private fun PasswordFieldPreview() {
    PasswordField(
        password = Password.fromRawInput(""),
    )
}
