package woowacourse.payments.ui.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import woowacourse.payments.R

@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.testTag("PasswordTextField"),
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
            if (isError) {
                Text(
                    text = stringResource(R.string.text_field_invalid_format_message),
                    color = Color.Red,
                    modifier = Modifier.testTag("PasswordTextFieldSupportingText"),
                )
            }
        },
        isError = isError,
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
private fun PasswordTextFieldPreview(
    @PreviewParameter(PasswordTextFieldPreviewParameterProvider::class) isError: Boolean,
) {
    val (password: String, setPassword: (String) -> Unit) = remember { mutableStateOf("") }

    PasswordTextField(
        value = password,
        onValueChange = setPassword,
        isError = isError,
    )
}

private class PasswordTextFieldPreviewParameterProvider : CollectionPreviewParameterProvider<Boolean>(listOf(false, true))
