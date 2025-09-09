package woowacourse.payments.ui.common

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class GroupedVisualTransformation(
    private val maxLength: Int,
    private val groupSize: Int,
    private val separator: String,
) : VisualTransformation {
    private val offsetMapping = object : OffsetMapping{
        override fun originalToTransformed(offset: Int): Int =
            if (offset in 1..maxLength) offset + ((offset - 1) / groupSize) * separator.length else offset

        override fun transformedToOriginal(offset: Int): Int {
            val group = offset / (groupSize + separator.length)
            val posInGroup = offset % (groupSize + separator.length)
            return (group * groupSize + posInGroup.coerceAtMost(groupSize)).coerceAtMost(maxLength)
        }
    }

    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = text.text.take(maxLength)

        val formatted =
            buildString {
                trimmed.forEachIndexed { index, char ->
                    if (index > 0 && index % groupSize == 0) append(separator)
                    append(char)
                }
            }

        return TransformedText(AnnotatedString(formatted), offsetMapping)
    }
}
