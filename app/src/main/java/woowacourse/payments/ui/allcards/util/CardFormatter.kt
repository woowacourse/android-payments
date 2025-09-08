package woowacourse.payments.ui.allcards.util

import androidx.compose.ui.text.AnnotatedString

object CardFormatter {
    fun formatCardNumber(text: String): String {
        val formattedText = StringBuilder()
        val cardNumberWithHyphens = text.chunked(4)
            .mapIndexed { index, s ->
                if (index > 1) "****" else s
            }
            .joinToString("-")
        cardNumberWithHyphens.forEachIndexed { index, it ->
            formattedText.append(it)
            formattedText.append(" ")
        }
        formattedText.deleteCharAt(formattedText.length - 1)
        return formattedText.toString()
    }

    fun formatExpirationDate(text: String): String {
        val formattedText = StringBuilder()
        val expirationDateWithSlash = text.chunked(2).joinToString("/")
        expirationDateWithSlash.forEachIndexed { index, it ->
            formattedText.append(it)
            formattedText.append(" ")
        }
        return formattedText.toString()


    }
}