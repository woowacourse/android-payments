package woowacourse.payments.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R
import woowacourse.payments.domain.validator.ValidationErrorType
import woowacourse.payments.ui.strings.getErrorMessage
import woowacourse.payments.ui.text.SeparatedVisualTransformation
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.theme.Grey40

@Composable
fun ExpirationDateField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    error: ValidationErrorType? = null,
) {
    val errorText = error?.let { getErrorMessage(it) }
    val expirationDateVisualTransformation =
        remember {
            SeparatedVisualTransformation(
                groupSize = EXPIRATION_DATE_CHUNK_SIZE,
                separator = EXPIRATION_DATE_SEPARATOR,
            )
        }

    OutlinedTextField(
        keyboardOptions =
            KeyboardOptions(
                keyboardType = KeyboardType.Number,
            ),
        modifier = modifier,
        value = value,
        onValueChange = {
            onValueChange(it.filter(Char::isDigit).take(EXPIRATION_DATE_LENGTH))
        },
        visualTransformation = expirationDateVisualTransformation,
        label = { Text(stringResource(R.string.expiration_date_label)) },
        placeholder = {
            Text(
                text = stringResource(R.string.expiration_date_placeholder),
                color = Grey40,
            )
        },
        isError = errorText != null,
        supportingText = { errorText?.let { Text(it) } },
        singleLine = true,
    )
}

@Preview(showBackground = true)
@Composable
private fun ExpirationDateFieldPreview() {
    AndroidpaymentsTheme {
        ExpirationDateField(
            value = "1226",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(0.5f),
        )
    }
}

private const val EXPIRATION_DATE_LENGTH = 4
private const val EXPIRATION_DATE_CHUNK_SIZE = 2
private const val EXPIRATION_DATE_SEPARATOR = " / "
