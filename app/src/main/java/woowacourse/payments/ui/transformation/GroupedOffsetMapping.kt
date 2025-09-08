package woowacourse.payments.ui.transformation

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping

class GroupedOffsetMapping(
    private val groupSize: Int,
    private val separatorLength: Int,
    private val raw: String,
    private val transformed: AnnotatedString,
) : OffsetMapping {
    override fun originalToTransformed(offset: Int): Int {
        val clamped = offset.coerceIn(0, raw.length)
        val separatorsBefore = clamped / groupSize
        return (clamped + separatorsBefore * separatorLength)
            .coerceAtMost(transformed.text.length)
    }

    override fun transformedToOriginal(offset: Int): Int {
        val target = offset.coerceIn(0, transformed.text.length)
        val originalLength = raw.length
        var original = 0
        var transformedPosition = 0
        while (original < originalLength && transformedPosition < target) {
            original++
            val groupsDone = original / groupSize
            transformedPosition = original + groupsDone * separatorLength
        }
        if (transformedPosition > target) original--
        return original.coerceIn(0, originalLength)
    }
}
