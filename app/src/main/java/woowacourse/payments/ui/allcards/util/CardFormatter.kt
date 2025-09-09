package woowacourse.payments.ui.allcards.util

object CardFormatter {
    fun formatCardNumber(text: String): String = text.chunked(4)
        .mapIndexed { index, s ->
            if (index > 1) "****" else s
        }
        .joinToString(" - ")

    fun formatExpirationDate(text: String): String = text.chunked(2).joinToString(" / ")
}