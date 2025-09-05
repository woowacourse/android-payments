package woowacourse.payments.ui.transformation

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import kotlin.math.min

class SeparatedTransformation(
    private val maxLength: Int,
    private val groupSize: Int,
    private val separator: String,
) : VisualTransformation {
    private val separatorLength = separator.length
    private val groupSpan = groupSize + separatorLength

    override fun filter(text: AnnotatedString): TransformedText {
        val rawText = text.text.take(maxLength)
        val displayedText = rawText.chunked(groupSize).joinToString(separator)

        val mapping =
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    val clamped = offset.coerceIn(0, rawText.length)
                    val groupsCompleted = clamped / groupSize
                    val transformedOffset = clamped + groupsCompleted * separatorLength
                    return transformedOffset.coerceAtMost(displayedText.length)
                }

                override fun transformedToOriginal(offset: Int): Int {
                    val clamped = offset.coerceIn(0, displayedText.length)
                    val groupsCompleted = clamped / groupSpan
                    val positionInGroupSpan = clamped % groupSpan
                    val digitsInsideGroup = min(positionInGroupSpan, groupSize)
                    val originalOffset = groupsCompleted * groupSize + digitsInsideGroup
                    return originalOffset.coerceAtMost(rawText.length)
                }
            }

        return TransformedText(AnnotatedString(displayedText), mapping)
    }
}
