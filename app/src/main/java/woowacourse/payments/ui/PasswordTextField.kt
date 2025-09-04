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

@Composable
fun PasswordTextField(modifier: Modifier = Modifier) {
    var password by remember { mutableStateOf("") }

    OutlinedTextField(
        value = password,
        onValueChange = { newValue: String ->
            password = newValue.substring(0, newValue.length.coerceAtMost(PASSWORD_LENGTH_MAX))
        },
        modifier = modifier,
        label = {
            Text(text = stringResource(R.string.password_label))
        },
        placeholder = {
            Text(text = stringResource(R.string.password_placeholder), color = Color.Gray)
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = PasswordVisualTransformation(),
    )
}

@Preview
@Composable
private fun PasswordTextFieldPreview() {
    PasswordTextField()
}

private const val PASSWORD_LENGTH_MAX: Int = 4