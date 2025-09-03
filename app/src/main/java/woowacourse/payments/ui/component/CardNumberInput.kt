package woowacourse.payments.ui.component

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
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import woowacourse.payments.domain.CardNumber

@Composable
fun CardNumberInput(
    cardNumber: CardNumber?,
    onCardNumberChange: (CardNumber?) -> Unit,
    modifier: Modifier = Modifier,
    showValidationError: Boolean = false,
) {
    var textFieldValue by remember { mutableStateOf(TextFieldValue()) }

    OutlinedTextField(
        value = textFieldValue,
        onValueChange = { newValue ->
            val digits = newValue.text.filter { it.isDigit() }.take(16)
            val formatted = digits.chunked(4).joinToString("-")
            val cursorPosition = formatted.length

            textFieldValue =
                TextFieldValue(
                    text = formatted,
                    selection = TextRange(cursorPosition),
                )
            onCardNumberChange(CardNumber.create(digits))
        },
        modifier = modifier,
        label = { Text(text = "카드 번호") },
        placeholder = { Text(text = "0000 - 0000 - 0000 - 0000", color = Color.LightGray) },
        isError = showValidationError && (cardNumber?.isValid != true),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    )
}
