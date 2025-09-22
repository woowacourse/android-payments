package woowacourse.payments.ui.common

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class DateVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = if (text.text.length > 4) text.text.substring(0..3) else text.text

        val out =
            buildString {
                for (i in trimmed.indices) {
                    append(trimmed[i])
                    if (i == 1 && i != trimmed.lastIndex) {
                        append(" / ")
                    }
                }
            }

        val dateOffsetTranslator =
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int =
                    when {
                        offset <= 0 -> 0
                        offset <= 2 -> offset
                        offset <= 4 -> offset + 3
                        else -> out.length
                    }

                override fun transformedToOriginal(offset: Int): Int =
                    when {
                        offset <= 0 -> 0
                        offset <= 2 -> offset
                        offset <= 3 -> 2
                        offset <= 4 -> 2
                        offset <= 5 -> 2
                        offset <= 7 -> offset - 3
                        else -> trimmed.length
                    }.coerceIn(0, trimmed.length)
            }

        return TransformedText(AnnotatedString(out), dateOffsetTranslator)
    }
}
