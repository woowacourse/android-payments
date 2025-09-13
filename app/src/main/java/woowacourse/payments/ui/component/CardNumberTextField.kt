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
import woowacourse.payments.domain.model.CardNumber
import woowacourse.payments.ui.transformation.NumberVisualTransformation

@Composable
fun CardNumberTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val separator = stringResource(R.string.card_number_separator)
    val visualTransformation = remember(separator) { NumberVisualTransformation(4, separator) }
    val textState = remember(value) { TextFieldValue(value, selection = TextRange(value.length)) }

    val digitsOnly = value.filter(Char::isDigit)
    val showError = digitsOnly.isNotEmpty() && CardNumber.from(digitsOnly) == null

    OutlinedTextField(
        value = textState,
        onValueChange = { input ->
            val filtered = input.text.filter(Char::isDigit).take(16)
            onValueChange(filtered)
        },
        label = { Text(stringResource(R.string.label_card_number)) },
        placeholder = { Text(stringResource(R.string.placeholder_card_number)) },
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        isError = showError,
        supportingText = {
            if (showError) {
                Text(
                    text = stringResource(R.string.error_card_number_invalid),
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
