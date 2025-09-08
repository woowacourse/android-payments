package woowacourse.payments.cardaddition.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R

@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
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
            if (value.isInvalidPassword) {
                Text(
                    text = stringResource(R.string.text_field_invalid_format_message),
                    color = Color.Red,
                )
            }
        },
        isError = value.isInvalidPassword,
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
    val (password: String, setPassword: (String) -> Unit) = remember { mutableStateOf("") }
    PasswordTextField(
        value = password,
        onValueChange = setPassword,
    )
}

private val String.isInvalidPassword: Boolean get() = isNotEmpty() && length != PASSWORD_LENGTH

const val PASSWORD_LENGTH: Int = 4
