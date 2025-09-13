package woowacourse.payments.ui.core

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class CardNumberVisualTransformation(
    private val separator: String,
    private val groupSize: Int,
    private val maxLength: Int,
) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val newText =
            buildString {
                text.forEachIndexed { index, char ->
                    append(char)

                    val indexInGroup = index % groupSize
                    val lastIndexOfGroup = groupSize - 1
                    val isLastOfGroup = indexInGroup == lastIndexOfGroup
                    val isNotLastIndex = index != maxLength - 1

                    if (isLastOfGroup && isNotLastIndex) append(separator)
                }
            }

        val offsetTranslator =
            object : OffsetMapping {
                private val actualLength = minOf(text.length, maxLength)
                private val separatorLength = separator.length
                private val separatorCount = if (actualLength == 0) 0 else (actualLength - 1) / groupSize
                private val transformedLength = actualLength + separatorCount * separatorLength

                override fun originalToTransformed(offset: Int): Int {
                    val newOffset = offset.coerceIn(0, actualLength)
                    val separatorCount = if (newOffset == 0) 0 else (newOffset - 1) / groupSize
                    return newOffset + separatorCount * separatorLength
                }

                override fun transformedToOriginal(offset: Int): Int {
                    val adjustedOffset = offset.coerceIn(0, transformedLength)
                    var currentOffset = adjustedOffset
                    for (k in 1..separatorCount) {
                        val sepStart = k * groupSize + (k - 1) * separatorLength
                        when {
                            adjustedOffset >= sepStart + separatorLength -> currentOffset -= separatorLength
                            adjustedOffset >= sepStart -> return k * groupSize
                            else -> break
                        }
                    }
                    return currentOffset
                }
            }

        return TransformedText(AnnotatedString(newText), offsetTranslator)
    }
}
