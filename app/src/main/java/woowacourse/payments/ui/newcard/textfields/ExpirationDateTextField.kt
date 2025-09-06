package woowacourse.payments.ui.newcard.textfields

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import woowacourse.payments.R
import woowacourse.payments.domain.ExpirationDate
import woowacourse.payments.ui.theme.Gray
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@Suppress("ktlint:standard:function-naming")
@Composable
fun ExpirationDateTextField(
    text: MutableState<String>,
    isError: MutableState<Boolean>,
) {
    val focusManager = LocalFocusManager.current
    val delimiter = LocalContext.current.getString(R.string.expiration_date_delimiter)

    val offsetMapping =
        object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                val multiplier = (offset - 1).coerceAtLeast(0) / EXPIRATION_DATE_CHUNK_SIZE
                return offset + delimiter.length * multiplier
            }

            override fun transformedToOriginal(offset: Int): Int {
                val multiplier =
                    ((offset - 1)).coerceAtLeast(0) / (EXPIRATION_DATE_CHUNK_SIZE + delimiter.length)
                return (offset - (delimiter.length * multiplier)).coerceAtMost(
                    EXPIRATION_DATE_CHUNK_SIZE * (multiplier + 1),
                )
            }
        }

    val transformation =
        object : VisualTransformation {
            override fun filter(text: AnnotatedString): TransformedText =
                TransformedText(
                    AnnotatedString(
                        text.text
                            .chunked(EXPIRATION_DATE_CHUNK_SIZE)
                            .joinToString(delimiter),
                    ),
                    offsetMapping,
                )
        }

    fun updateValue(newValue: String) {
        val filteredValue: String =
            newValue.filter(Char::isDigit).take(EXPIRATION_DATE_REQUIRED_LENGTH)
        text.value = filteredValue

        isError.value =
            runCatching {
                ExpirationDate(
                    YearMonth.parse(
                        filteredValue,
                        DateTimeFormatter.ofPattern("MMyy"),
                    ),
                )
            }.isFailure

        if (!isError.value && filteredValue.length == EXPIRATION_DATE_REQUIRED_LENGTH) {
            focusManager.moveFocus(FocusDirection.Next)
        }
    }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(0.5F),
        value = text.value,
        onValueChange = { newValue: String -> updateValue(newValue) },
        singleLine = true,
        visualTransformation = transformation,
        label = { Text(stringResource(R.string.expiration_date_label)) },
        placeholder = {
            Text(
                text = stringResource(R.string.expiration_date_placeholder),
                color = Gray,
            )
        },
        supportingText = {
            Text(
                if (isError.value) {
                    stringResource(R.string.expiration_date_error_message)
                } else {
                    ""
                },
            )
        },
        isError = isError.value,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        keyboardActions = KeyboardActions(onDone = { focusManager.moveFocus(FocusDirection.Next) }),
    )
}

private const val EXPIRATION_DATE_REQUIRED_LENGTH = 4
private const val EXPIRATION_DATE_CHUNK_SIZE = 2
