package woowacourse.payments.ui.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class GroupedSeparatorVisualTransformation(
    private val groupSizes: IntArray,
    private val separator: String,
    private val maxDigits: Int = groupSizes.sum(),
) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val digits = text.text.filter(Char::isDigit).take(maxDigits)

        val sb = StringBuilder(digits.length + groupSizes.size - 1)
        val cutPoints = mutableListOf<Int>()
        var cursor = 0
        var consumed = 0
        for (groupSize in groupSizes) {
            val end = (consumed + groupSize).coerceAtMost(digits.length)
            if (consumed < end) {
                sb.append(digits, consumed, end)
                cursor += end - consumed
                consumed = end
            }
            if (consumed < digits.length) {
                sb.append(separator)
                cutPoints += cursor
                cursor += 1
            } else {
                break
            }
        }
        val transformed = sb.toString()

        // 동적 OffsetMapping
        val offsetMapping =
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    val o = offset.coerceIn(0, digits.length)
                    var extra = 0
                    var acc = 0
                    for (groupSize in groupSizes) {
                        val next = acc + groupSize
                        if (o > next && next < digits.length) extra += 1
                        acc = next
                    }
                    return (o + extra).coerceAtMost(transformed.length)
                }

                override fun transformedToOriginal(offset: Int): Int {
                    val t = offset.coerceIn(0, transformed.length)
                    val hyphensBefore = cutPoints.count { it < t }
                    return (t - hyphensBefore).coerceIn(0, digits.length)
                }
            }
        return TransformedText(AnnotatedString(transformed), offsetMapping)
    }
}
