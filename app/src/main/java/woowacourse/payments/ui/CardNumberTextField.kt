package woowacourse.payments.ui

import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R
import java.lang.Character.isDigit

@Composable
fun CardNumberTextField(modifier: Modifier = Modifier) {
    var cardNumber: String by remember { mutableStateOf("") }

    OutlinedTextField(
        value = cardNumber,
        onValueChange = { newValue: String ->
            val newCardNumber: String = newValue.filter(::isDigit)
            cardNumber = newCardNumber.take(CARD_NUMBER_LENGTH_MAX)
        },
        modifier = modifier,
        label = {
            Text(text = stringResource(R.string.card_number_label))
        },
        placeholder = {
            Text(
                text = stringResource(R.string.card_number_placeholder),
                color = Color.Gray,
            )
        },
        visualTransformation = ::filteredCardNumber,
        keyboardOptions =
            KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next,
            ),
    )
}

@Preview
@Composable
private fun CardNumberTextFieldPreview() {
    CardNumberTextField(modifier = Modifier.fillMaxWidth())
}

private fun filteredCardNumber(text: AnnotatedString): TransformedText {
    val trimmedText: String = text.take(CARD_NUMBER_LENGTH_MAX).toString()

    val transformedText: String =
        trimmedText
            .mapIndexed { index: Int, char: Char ->
                if (index % 4 == 3 && index != CARD_NUMBER_LENGTH_MAX - 1) {
                    char + DELIMITER
                } else {
                    char
                }
            }.joinToString(separator = "")

    return TransformedText(AnnotatedString(transformedText), creditCardOffsetTranslator)
}

private val creditCardOffsetTranslator =
    object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            val delimiterCount: Int = (offset / 4).coerceAtMost(DELIMITER_COUNT_MAX)
            return offset + DELIMITER.length * delimiterCount
        }

        override fun transformedToOriginal(offset: Int): Int {
            val delimiterCount: Int = (offset / 5).coerceAtMost(DELIMITER_COUNT_MAX)
            return offset - DELIMITER.length * delimiterCount
        }
    }

private const val CARD_NUMBER_LENGTH_MAX: Int = 16
private const val DELIMITER: String = " - "
private const val DELIMITER_COUNT_MAX: Int = 3
