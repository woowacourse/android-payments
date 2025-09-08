package woowacourse.payments.ui.transformation

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class GroupedVisualTransformation(
    private val groupSize: Int,
    private val separator: String,
) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val raw = text.text

        val formatted =
            buildString {
                raw.forEachIndexed { index, char ->
                    append(char)
                    val endOfGroup = (index + 1) % groupSize == 0
                    val notLast = (index + 1) < raw.length
                    if (endOfGroup && notLast) append(separator)
                }
            }
        val transformed = AnnotatedString(formatted)
        val separatorLength = separator.length

        val mapping =
            GroupedOffsetMapping(
                groupSize = groupSize,
                separatorLength = separatorLength,
                raw = raw,
                transformed = transformed,
            )
        return TransformedText(transformed, mapping)
    }
}
