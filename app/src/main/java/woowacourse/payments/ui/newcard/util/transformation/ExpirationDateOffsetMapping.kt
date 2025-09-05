package woowacourse.payments.ui.newcard.util.transformation

import androidx.compose.ui.text.input.OffsetMapping

class ExpirationDateOffsetMapping(
    private val trimmed: String,
    private val formattedText: String,
) : OffsetMapping {
    override fun originalToTransformed(offset: Int): Int {
        if (offset <= 0) return 0
        if (offset >= trimmed.length) return formattedText.length

        val sepCount = if (offset > 2) 1 else 0
        return offset + sepCount * 3
    }

    override fun transformedToOriginal(offset: Int): Int {
        if (offset <= 0) return 0
        if (offset >= formattedText.length) return trimmed.length

        val sepCount = if (offset > 5) 1 else 0 // 2 + separator(" / ") 길이 = 5
        return (offset - sepCount * 3).coerceIn(0, trimmed.length)
    }
}
