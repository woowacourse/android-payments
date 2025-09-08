package woowacourse.payments.ui.allcards.util

import androidx.compose.ui.text.AnnotatedString

object CardFormatter {
    fun formatCardNumber(text: String): String = text.chunked(4)
        .mapIndexed { index, s ->
            if (index > 1) "****" else s
        }
        .joinToString(" - ")

    fun formatExpirationDate(text: String): String = text.chunked(2).joinToString(" / ")
}