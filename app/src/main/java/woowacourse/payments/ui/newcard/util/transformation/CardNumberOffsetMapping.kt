package woowacourse.payments.ui.newcard.util.transformation

import androidx.compose.ui.text.input.OffsetMapping

class CardNumberOffsetMapping(
    private val trimmed: String,
    private val formattedText: String,
) : OffsetMapping {
    override fun originalToTransformed(offset: Int): Int {
        if (offset <= 0) return 0
        if (offset >= trimmed.length) return formattedText.length

        val sepCount = (offset - 1) / 4
        return offset + sepCount * 3
    }

    override fun transformedToOriginal(offset: Int): Int {
        if (offset <= 0) return 0
        if (offset >= formattedText.length) return trimmed.length

        val sepCount = offset / 7
        return (offset - sepCount * 3).coerceIn(0, trimmed.length)
    }
}
