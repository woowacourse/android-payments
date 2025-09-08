package woowacourse.payments.ui.component.cardaddition

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
import java.lang.Character.isDigit

@Composable
fun ExpiredDateTextField(modifier: Modifier = Modifier) {
    var expiredDate by remember { mutableStateOf("") }

    OutlinedTextField(
        value = expiredDate,
        onValueChange = { newValue: String ->
            val newDate = newValue.filter(::isDigit)
            expiredDate = newDate.take(newDate.length.coerceAtMost(EXPIRED_DATE_LENGTH_MAX))
        },
        modifier = modifier,
        label = { Text(text = stringResource(R.string.expired_date_label)) },
        placeholder = {
            Text(
                text = stringResource(R.string.expired_date_placeholder),
                color = Color.Gray
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = { text: AnnotatedString ->
            val trimmed = text.take(text.length.coerceAtMost(EXPIRED_DATE_LENGTH_MAX))
            var out = ""
            trimmed.forEachIndexed { index: Int, char: Char ->
                out += char
                if (index == 1) out += EXPIRED_DATE_DELIMITER
            }
            val dateOffsetTranslator = object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    if (offset <= 1) return offset
                    return offset + EXPIRED_DATE_DELIMITER.length
                }

                override fun transformedToOriginal(offset: Int): Int {
                    if (offset <= 2) return offset
                    return offset - EXPIRED_DATE_DELIMITER.length
                }

            }
            TransformedText(AnnotatedString(out), dateOffsetTranslator)
        }
    )
}

@Preview
@Composable
private fun ExpiredDateTextFieldPreview() {
    ExpiredDateTextField()
}

private const val EXPIRED_DATE_LENGTH_MAX: Int = 4
private const val EXPIRED_DATE_DELIMITER: String = " / "