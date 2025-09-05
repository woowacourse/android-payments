package woowacourse.payments.ui.common

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class NumberVisualTransformation(
    private val chunkSize: Int,
    private val separator: String,
) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val originalText = text.text
        if (originalText.isEmpty()) {
            return TransformedText(AnnotatedString(""), OffsetMapping.Identity)
        }

        val separatorRanges = ArrayList<IntRange>()
        val formattedText =
            buildString {
                originalText.forEachIndexed { index, char ->
                    append(char)
                    val nextIndex = index + 1
                    val needSeparator =
                        (nextIndex % chunkSize == 0) && (nextIndex < originalText.length)
                    if (needSeparator) {
                        val separatorStart = length
                        append(separator)
                        separatorRanges += separatorStart until (separatorStart + separator.length)
                    }
                }
            }

        val offsetMapping =
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    val boundedOriginalOffset = offset.coerceIn(0, originalText.length)
                    val separatorCountBefore =
                        (boundedOriginalOffset / chunkSize).coerceAtMost((originalText.length - 1) / chunkSize)
                    return boundedOriginalOffset + separatorCountBefore * separator.length
                }

                override fun transformedToOriginal(offset: Int): Int {
                    val boundedTransformedOffset = offset.coerceIn(0, formattedText.length)

                    separatorRanges.firstOrNull { boundedTransformedOffset in it }?.let { range ->
                        val boundaryIndex = separatorRanges.indexOf(range) + 1
                        return (boundaryIndex * chunkSize).coerceIn(0, originalText.length)
                    }

                    val completedSeparators =
                        separatorRanges.count { it.last < boundedTransformedOffset }
                    val originalOffset =
                        boundedTransformedOffset - completedSeparators * separator.length
                    return originalOffset.coerceIn(0, originalText.length)
                }
            }

        return TransformedText(AnnotatedString(formattedText), offsetMapping)
    }
}
