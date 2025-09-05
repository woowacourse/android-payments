package woowacourse.payments.ui.newcard.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.payments.designsystem.theme.AndroidpaymentsTheme

@Composable
fun DigitsTextField(
    value: String,
    label: String,
    placeholder: String,
    maxLength: Int,
    modifier: Modifier = Modifier,
    grouping: IntArray? = null,
    separator: String = " - ",
    colors: TextFieldColors = formTextFieldColors(),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onValueChange: (String) -> Unit,
    onImeAction: () -> Unit = {},
) {
    var textValue by remember(value, grouping, separator) {
        val formattedText = formatWithGrouping(value, grouping, separator)
        val caretPos =
            rawIndexToFormattedIndex(
                raw = value,
                rawIndex = value.length,
                grouping = grouping,
                separator = separator,
            )
        mutableStateOf(TextFieldValue(text = formattedText, selection = TextRange(caretPos)))
    }

    LaunchedEffect(value, grouping, separator) {
        val formattedText = formatWithGrouping(value, grouping, separator)
        val caretPos =
            rawIndexToFormattedIndex(
                raw = value,
                rawIndex = value.length,
                grouping = grouping,
                separator = separator,
            )
        textValue = textValue.copy(text = formattedText, selection = TextRange(caretPos))
    }

    OutlinedTextField(
        modifier = modifier,
        value = textValue,
        onValueChange = { newTextFieldValue ->
            val proposedRaw = newTextFieldValue.text.filter(Char::isDigit).take(maxLength)

            val rawCaretIndex =
                formattedIndexToRawIndex(
                    formatted = newTextFieldValue.text,
                    formattedIndex = newTextFieldValue.selection.start,
                    separator = separator,
                ).coerceIn(0, proposedRaw.length)

            if (proposedRaw != value) {
                onValueChange(proposedRaw)
            }

            val formattedText = formatWithGrouping(proposedRaw, grouping, separator)
            val formattedCaretIndex =
                rawIndexToFormattedIndex(
                    raw = proposedRaw,
                    rawIndex = rawCaretIndex,
                    grouping = grouping,
                    separator = separator,
                )

            textValue =
                TextFieldValue(
                    text = formattedText,
                    selection = TextRange(formattedCaretIndex),
                )
        },
        label = { Text(label) },
        placeholder = { Text(placeholder) },
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions =
            KeyboardActions(
                onNext = { onImeAction() },
                onDone = { onImeAction() },
            ),
        singleLine = true,
        colors = colors,
    )
}

private fun formatWithGrouping(
    raw: String,
    grouping: IntArray?,
    separator: String,
): String {
    if (grouping == null || grouping.isEmpty()) return raw
    val chunks = mutableListOf<String>()
    var index = 0
    for (groupSize in grouping) {
        if (index >= raw.length) break
        val end = (index + groupSize).coerceAtMost(raw.length)
        chunks += raw.substring(index, end)
        index = end
    }
    if (index < raw.length) chunks += raw.substring(index)
    return chunks.joinToString(separator)
}

private fun formattedIndexToRawIndex(
    formatted: String,
    formattedIndex: Int,
    separator: String,
): Int {
    if (separator.isEmpty()) return formattedIndex.coerceIn(0, formatted.length)
    var rawCount = 0
    var index = 0
    while (index < formattedIndex && index < formatted.length) {
        if (formatted.startsWith(separator, index)) {
            index += separator.length
        } else {
            if (formatted[index].isDigit()) rawCount++
            index++
        }
    }
    return rawCount
}

private fun rawIndexToFormattedIndex(
    raw: String,
    rawIndex: Int,
    grouping: IntArray?,
    separator: String,
): Int {
    if (grouping == null || grouping.isEmpty() || separator.isEmpty()) {
        return rawIndex.coerceIn(0, raw.length)
    }
    val clampedRawIndex = rawIndex.coerceIn(0, raw.length)
    var formattedIndex = clampedRawIndex
    var consumed = 0
    for (groupIndex in grouping.indices) {
        val boundary = consumed + grouping[groupIndex]
        if (clampedRawIndex > boundary && raw.length > boundary) {
            formattedIndex += separator.length
        }
        consumed = boundary
    }
    return formattedIndex
}

@Preview
@Composable
private fun DigitsTextFieldPreview() {
    AndroidpaymentsTheme {
        DigitsTextField(
            value = "1234567890123456",
            onValueChange = {},
            label = "카드 번호",
            placeholder = "0000 - 0000 - 0000 - 0000",
            maxLength = 16,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
