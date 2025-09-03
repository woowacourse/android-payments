package woowacourse.payments.ui.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
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
        label = { Text("카드 번호") },
        placeholder = {
            Text(
                text = "0000 - 0000 - 0000 - 0000",
                color = Grey40,
            )
        },
        singleLine = true,
    )
}

private fun formatCardNumber(input: TextFieldValue): TextFieldValue {
    val digits = input.text.filter(Char::isDigit).take(16)
    val formatted = digits.chunked(4).joinToString(" - ")
    return TextFieldValue(
        text = formatted,
        selection = TextRange(formatted.length),
    )
}
