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
            cardNumber = newCardNumber.take(CARD_NUMBER_LENGTH)
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
        supportingText = {
            if (cardNumber.isInvalidCardNumber) {
                Text(
                    text = stringResource(R.string.text_field_invalid_format_message),
                    color = Color.Red,
                )
            }
        },
        isError = cardNumber.isInvalidCardNumber,
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

private val String.isInvalidCardNumber: Boolean get() = isNotEmpty() && length != CARD_NUMBER_LENGTH

private fun filteredCardNumber(text: AnnotatedString): TransformedText {
    val trimmedText: String = text.take(CARD_NUMBER_LENGTH).toString()

    val transformedText: String =
        trimmedText
            .mapIndexed { index: Int, char: Char ->
                if (index % CARD_GROUP_LENGTH == CARD_GROUP_LENGTH - 1 && index != CARD_NUMBER_LENGTH - 1) {
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
            val delimiterCount: Int = (offset / CARD_GROUP_LENGTH).coerceAtMost(DELIMITER_COUNT_MAX)
            return offset + DELIMITER.length * delimiterCount
        }

        override fun transformedToOriginal(offset: Int): Int {
            val delimiterCount: Int = (offset / 5).coerceAtMost(DELIMITER_COUNT_MAX)
            return offset - DELIMITER.length * delimiterCount
        }
    }

private const val CARD_NUMBER_LENGTH: Int = 16
private const val CARD_GROUP_LENGTH: Int = 4
private const val DELIMITER: String = " - "
private const val DELIMITER_COUNT_MAX: Int = 3
