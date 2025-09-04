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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R

private const val PASSWORD_LENGTH_MAX: Int = 4

@Composable
fun PasswordTextField(
    value: String,
    onPasswordChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        onValueChange = { newValue: String ->
            val newPassword = newValue.take(PASSWORD_LENGTH_MAX)
            onPasswordChange(newPassword)
        },
        modifier = modifier,
        label = {
            Text(text = stringResource(R.string.password_label))
        },
        placeholder = {
            Text(text = stringResource(R.string.password_placeholder), color = Color.Gray)
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        visualTransformation = PasswordVisualTransformation(),
    )
}

@Preview
@Composable
private fun PasswordTextFieldPreview() {
    var text by remember { mutableStateOf("") }
    PasswordTextField(value = text, onPasswordChange = { text = it })
}
