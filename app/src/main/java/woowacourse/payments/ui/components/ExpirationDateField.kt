package woowacourse.payments.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import woowacourse.payments.ui.theme.Grey40

@Composable
fun ExpirationDateField(
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
            onValueChange(formatExpirationDate(input))
        },
        label = { Text(text = "만료일") },
        placeholder = {
            Text(
                text = "MM / YY",
                color = Grey40,
            )
        },
        singleLine = true,
    )
}

private fun formatExpirationDate(input: TextFieldValue): TextFieldValue {
    val digits = input.text.filter(Char::isDigit).take(4)
    val formatted = digits.chunked(2).joinToString(" / ")
    return TextFieldValue(
        text = formatted,
        selection = TextRange(formatted.length),
    )
}

@Preview(showBackground = true)
@Composable
private fun ExpirationDateFieldPreview() {
    AndroidpaymentsTheme {
        ExpirationDateField(
            value = TextFieldValue("1226"),
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(0.5f),
        )
    }
}
