package woowacourse.payments.ui

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
fun ExpiredDateTextField(modifier: Modifier = Modifier) {
    var expiredDate: String by remember { mutableStateOf("") }

    OutlinedTextField(
        value = expiredDate,
        onValueChange = { newValue: String ->
            val newDate: String = newValue.filter(::isDigit)
            expiredDate = newDate.take(EXPIRED_DATE_LENGTH)
        },
        modifier = modifier,
        label = {
            Text(text = stringResource(R.string.expired_date_label))
        },
        placeholder = {
            Text(
                text = stringResource(R.string.expired_date_placeholder),
                color = Color.Gray,
            )
        },
        supportingText = {
            if (expiredDate.isInvalidExpiredDate) {
                Text(
                    text = stringResource(R.string.text_field_invalid_format_message),
                    color = Color.Red,
                )
            }
        },
        isError = expiredDate.isInvalidExpiredDate,
        visualTransformation = ::filteredExpiredDate,
        keyboardOptions =
            KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next,
            ),
    )
}

@Preview
@Composable
private fun ExpiredDateTextFieldPreview() {
    ExpiredDateTextField()
}

private val String.isInvalidExpiredDate: Boolean get() = isNotEmpty() && length != EXPIRED_DATE_LENGTH

private fun filteredExpiredDate(text: AnnotatedString): TransformedText {
    val trimmedText: CharSequence = text.take(EXPIRED_DATE_LENGTH)

    val transformedText: String =
        trimmedText
            .mapIndexed { index: Int, char: Char ->
                if (index == 1) {
                    char + EXPIRED_DATE_DELIMITER
                } else {
                    char
                }
            }.joinToString(separator = "")

    return TransformedText(AnnotatedString(transformedText), dateOffsetTranslator)
}

private val dateOffsetTranslator =
    object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset < 2) return offset
            return offset + EXPIRED_DATE_DELIMITER.length
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset < 3) return offset
            return offset - EXPIRED_DATE_DELIMITER.length
        }
    }

private const val EXPIRED_DATE_LENGTH: Int = 4
private const val EXPIRED_DATE_DELIMITER: String = " / "
