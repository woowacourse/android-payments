package woowacourse.payments.newcard.transformation

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

val expiredDateVisualTransformation =
    object : VisualTransformation {
        override fun filter(text: AnnotatedString): TransformedText {
            val trimmed = if (text.text.length >= 5) text.text.substring(0, 4) else text.text
            var out = ""
            for (i in trimmed.indices) {
                out += trimmed[i]
                if (i == 1) out += "/"
            }
            return TransformedText(AnnotatedString(out), expiredDateOffsetTranslator)
        }
    }

val expiredDateOffsetTranslator =
    object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int =
            when {
                offset <= 1 -> offset
                offset <= 4 -> offset + 1
                else -> 5
            }

        override fun transformedToOriginal(offset: Int): Int =
            when {
                offset <= 2 -> offset
                offset <= 5 -> offset - 1
                else -> 4
            }
    }
