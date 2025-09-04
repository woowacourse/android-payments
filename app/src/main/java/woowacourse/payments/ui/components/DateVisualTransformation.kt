package woowacourse.payments.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun DateVisualTransformation(): VisualTransformation {
    return VisualTransformation { text ->
        val trimmed = if (text.text.length > 4) text.text.substring(0..3) else text.text

        val out = buildString {
            for (i in trimmed.indices) {
                append(trimmed[i])
                if (i == 1 && i != trimmed.lastIndex) {
                    append("/")
                }
            }
        }

        val transformedLength = out.length

        val dateOffsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return when {
                    offset <= 1 -> offset
                    offset <= 3 -> offset + 1
                    else -> transformedLength
                }.coerceIn(0, transformedLength)
            }

            override fun transformedToOriginal(offset: Int): Int {
                return when {
                    offset <= 2 -> offset
                    offset <= 5 -> offset - 1
                    else -> trimmed.length
                }.coerceIn(0, trimmed.length)
            }
        }

        TransformedText(
            AnnotatedString(out),
            dateOffsetTranslator
        )
    }
}