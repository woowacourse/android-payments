package woowacourse.payments.ui.newcard.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.R
import woowacourse.payments.domain.ExpiredDate
import java.lang.Character.isDigit
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@Composable
fun ExpiredDateTextField(
    expiredDate: String,
    onExpirationDateChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var isFocused by remember { mutableStateOf(false) }
    val currentError = remember(expiredDate, isFocused) {
        if (isFocused || expiredDate.isEmpty()) {
            null
        } else {
            runCatching {
                val digits = expiredDate.filter { it.isDigit() }.take(4)
                ExpiredDate(value = YearMonth.parse(digits, DateTimeFormatter.ofPattern("MMyy")))
                null
            }.getOrElse { it.message }
        }
    }

    OutlinedTextField(
        value = expiredDate,
        onValueChange = { newValue: String ->
            val newDate = newValue.filter(::isDigit)
            onExpirationDateChange(newDate.take(newDate.length.coerceAtMost(EXPIRED_DATE_LENGTH_MAX)))
        },
        modifier = modifier.onFocusChanged{ state ->
            isFocused = state.isFocused
        },
        label = { Text(text = stringResource(R.string.expired_date_label)) },
        placeholder = {
            Text(
                text = stringResource(R.string.expired_date_placeholder),
                color = Color.Gray
            )
        },
        isError = !isFocused && currentError != null,
        supportingText = {
            if (!isFocused && currentError != null) {
                Text(
                    text = currentError,
                    color = MaterialTheme.colorScheme.error
                )
            }
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
    var expiredDate by remember { mutableStateOf("") }
    ExpiredDateTextField(
        expiredDate = expiredDate,
        onExpirationDateChange = { expiredDate = it },
    )
}

private const val EXPIRED_DATE_LENGTH_MAX: Int = 4
private const val EXPIRED_DATE_DELIMITER: String = " / "