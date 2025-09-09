package woowacourse.payments.ui.formatter

import androidx.compose.ui.text.AnnotatedString

class CardNumberFormatter {
    val visualTransformation =
        UniformlySeparatingVisualTransformation(
            CARD_NUMBER_CHUNK_SIZE,
            CARD_NUMBER_SEPARATOR,
        )

    fun format(text: String): String = visualTransformation.filter(AnnotatedString(text)).text.text

    companion object {
        private const val CARD_NUMBER_CHUNK_SIZE = 4
        private const val CARD_NUMBER_SEPARATOR = " - "
    }
}
