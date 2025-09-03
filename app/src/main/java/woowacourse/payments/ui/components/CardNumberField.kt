package woowacourse.payments.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.theme.Grey40

@Composable
fun CardNumberField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        keyboardOptions =
            KeyboardOptions(
                keyboardType = KeyboardType.Number,
            ),
        modifier = modifier,
        value = value,
        onValueChange = { input ->
            onValueChange(formatCardNumber(input))
        },
        label = { Text(stringResource(R.string.card_number_label)) },
        placeholder = {
            Text(
                text = stringResource(R.string.card_number_placeholder),
                color = Grey40,
            )
        },
        singleLine = true,
    )
}

private fun formatCardNumber(input: TextFieldValue): TextFieldValue {
    val digits = input.text.filter(Char::isDigit).take(CARD_NUMBER_MAX_LENGTH)
    val formatted = digits.chunked(CARD_NUMBER_CHUNK_SIZE).joinToString(CARD_NUMBER_SEPARATOR)
    return TextFieldValue(
        text = formatted,
        selection = TextRange(formatted.length),
    )
}

@Preview(showBackground = true)
@Composable
private fun CardNumberFieldPreview() {
    AndroidpaymentsTheme {
        CardNumberField(
            value = TextFieldValue("1234-5678-9012-3456"),
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

private const val CARD_NUMBER_MAX_LENGTH = 16
private const val CARD_NUMBER_CHUNK_SIZE = 4
private const val CARD_NUMBER_SEPARATOR = " - "
