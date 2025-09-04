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
                    if (i % 4 == 3 && i != 15) append("-")
                }
            }

        val creditCardOffsetTranslator =
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int =
                    when {
                        offset <= 3 -> offset
                        offset <= 7 -> offset + 1
                        offset <= 11 -> offset + 2
                        offset <= 16 -> offset + 3
                        else -> out.length
                    }

                override fun transformedToOriginal(offset: Int): Int =
                    when {
                        offset <= 4 -> offset
                        offset <= 9 -> offset - 1
                        offset <= 14 -> offset - 2
                        offset <= 19 -> offset - 3
                        else -> trimmed.length
                    }
            }

        return TransformedText(AnnotatedString(out), creditCardOffsetTranslator)
    }
}
