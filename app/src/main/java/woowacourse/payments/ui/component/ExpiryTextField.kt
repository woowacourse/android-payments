package woowacourse.payments.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import woowacourse.payments.R
import woowacourse.payments.domain.model.Expiry
import woowacourse.payments.ui.transformation.NumberVisualTransformation

@Composable
fun ExpiryTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val separator = stringResource(R.string.expiry_separator)
    val visual = remember(separator) { NumberVisualTransformation(2, separator) }
    val textState = remember(value) { TextFieldValue(value, selection = TextRange(value.length)) }

    val digitsOnly = value.filter(Char::isDigit)
    val showError = digitsOnly.isNotEmpty() && Expiry.from(digitsOnly) == null

    OutlinedTextField(
        value = textState,
        onValueChange = { input ->
            val filtered = input.text.filter(Char::isDigit).take(4)
            onValueChange(filtered)
        },
        label = { Text(stringResource(R.string.label_expiry)) },
        placeholder = { Text(stringResource(R.string.placeholder_expiry)) },
        visualTransformation = visual,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        isError = showError,
        supportingText = {
            if (showError) {
                Text(
                    text = stringResource(R.string.error_expiry_invalid),
                    color = MaterialTheme.colorScheme.error,
                )
            }
        },
        modifier =
            modifier
                .padding(bottom = 30.dp)
                .padding(horizontal = 24.dp),
    )
}
