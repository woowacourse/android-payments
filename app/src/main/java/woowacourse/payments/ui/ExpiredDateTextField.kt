package woowacourse.payments.ui

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
            expiredDate = newDate.take(EXPIRED_DATE_LENGTH_MAX)
        },
        modifier = modifier,
        label = {
            Text(text = stringResource(R.string.expired_date_label))
        },
        placeholder = {
            Text(
                text = stringResource(R.string.expired_date_placeholder),
                color = Color.Gray
            )
        },
        visualTransformation = ::filteredExpiredDate
    )
}

@Preview
@Composable
private fun ExpiredDateTextFieldPreview() {
    ExpiredDateTextField()
}

private fun filteredExpiredDate(text: AnnotatedString): TransformedText {
    val trimmedText: CharSequence = text.take(EXPIRED_DATE_LENGTH_MAX)

    val transformedText: String = trimmedText.mapIndexed { index: Int, char: Char ->
        if (index == 1) char + EXPIRED_DATE_DELIMITER
        else char
    }.joinToString(separator = "")

    return TransformedText(AnnotatedString(transformedText), dateOffsetTranslator)
}

private val dateOffsetTranslator = object : OffsetMapping {
    override fun originalToTransformed(offset: Int): Int {
        if (offset < 2) return offset
        return offset + EXPIRED_DATE_DELIMITER.length
    }

    override fun transformedToOriginal(offset: Int): Int {
        if (offset < 3) return offset
        return offset - EXPIRED_DATE_DELIMITER.length
    }
}

private const val EXPIRED_DATE_LENGTH_MAX: Int = 4
private const val EXPIRED_DATE_DELIMITER: String = " / "