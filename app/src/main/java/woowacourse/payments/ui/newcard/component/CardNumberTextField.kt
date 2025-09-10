package woowacourse.payments.ui.newcard.component

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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R
import woowacourse.payments.ui.theme.Gray79
import java.lang.Character.isDigit

@Composable
fun CardNumberTextField(
    number: String,
    numberErrorMessage: String? = null,
    onNumberChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = number,
        onValueChange = { newValue: String ->
            val newNumbers = newValue.filter(::isDigit)
            onNumberChange(
                newNumbers.take(newNumbers.length.coerceAtMost(CARD_NUMBER_LENGTH_MAX))
            )
        },
        modifier = modifier,
        label = {
            Text(text = stringResource(R.string.card_number_label))
        },
        placeholder = {
            Text(
                text = stringResource(R.string.card_number_placeholder),
                color = Gray79,
            )
        },
        isError = numberErrorMessage != null,
        supportingText = {
            if (numberErrorMessage != null) {
                Text(
                    text = numberErrorMessage,
                    color = Color.Red
                )
            } else {
                null
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = ::creditCardFilter
    )
}

@Preview
@Composable
private fun CardNumberTextFieldPreview() {
    var number by remember { mutableStateOf("") }
    CardNumberTextField(onNumberChange = { number = it }, number = number)
}

private fun creditCardFilter(text: AnnotatedString): TransformedText {
    val trimmed = text.substring(0, text.length.coerceAtMost(CARD_NUMBER_LENGTH_MAX))
    var out = ""
    trimmed.forEachIndexed { index, ch ->
        out += ch
        if (index % 4 == 3 && index != CARD_NUMBER_LENGTH_MAX - 1) out += CARD_NUMBER_DELIMITER
    }

    val creditCardOffsetTranslator =
        object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset <= 3) return offset
                if (offset <= 7) return offset + CARD_NUMBER_DELIMITER.length * 1
                if (offset <= 11) return offset + CARD_NUMBER_DELIMITER.length * 2
                return offset + CARD_NUMBER_DELIMITER.length * 3
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset <= 4) return offset
                if (offset <= 9) return offset - CARD_NUMBER_DELIMITER.length * 1
                if (offset <= 14) return offset - CARD_NUMBER_DELIMITER.length * 2
                return offset - CARD_NUMBER_DELIMITER.length * 3
            }
        }

    return TransformedText(AnnotatedString(out), creditCardOffsetTranslator)
}

private const val CARD_NUMBER_DELIMITER: String = " - "
private const val CARD_NUMBER_LENGTH_MAX: Int = 16