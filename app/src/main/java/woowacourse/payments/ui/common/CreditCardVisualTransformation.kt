package woowacourse.payments.ui.common

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class CreditCardVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = if (text.text.length >= 16) text.text.substring(0..15) else text.text
        val out =
            buildString {
                for (i in trimmed.indices) {
                    append(trimmed[i])
                    if ((i + 1) % 4 == 0 && i != trimmed.lastIndex) {
                        append(" - ")
                    }
                }
            }

        val creditCardOffsetTranslator =
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int =
                    when {
                        offset <= 4 -> offset
                        offset <= 8 -> offset + 3
                        offset <= 12 -> offset + 6
                        offset <= 16 -> offset + 9
                        else -> out.length
                    }

                override fun transformedToOriginal(offset: Int): Int =
                    when {
                        offset <= 4 -> offset
                        offset <= 9 -> offset - 3
                        offset <= 14 -> offset - 6
                        offset <= 19 -> offset - 9
                        else -> trimmed.length
                    }
            }

        return TransformedText(AnnotatedString(out), creditCardOffsetTranslator)
    }
}
