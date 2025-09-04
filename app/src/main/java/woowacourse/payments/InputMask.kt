package woowacourse.payments

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TransformedText

sealed class InputMask {
    abstract fun apply(text: AnnotatedString): TransformedText

    data object None : InputMask() {
        override fun apply(text: AnnotatedString) = TransformedText(text, OffsetMapping.Identity)
    }

    data object CardNumber : InputMask() {
        override fun apply(text: AnnotatedString) = creditCardFilter(text)
    }

    data object Expiry : InputMask() {
        override fun apply(text: AnnotatedString) = expiryFilter(text)
    }

    data object Password : InputMask() {
        override fun apply(text: AnnotatedString) = PasswordVisualTransformation().filter(text)
    }
}

private fun creditCardFilter(text: AnnotatedString): TransformedText {
    val trimmed = if (text.text.length >= 16) text.text.substring(0..15) else text.text
    var out = ""
    for (i in trimmed.indices) {
        out += trimmed[i]
        if (i % 4 == 3 && i != 15) out += "-"
    }
    val creditCardOffsetTranslator =
        object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset <= 3) return offset
                if (offset <= 7) return offset + 1
                if (offset <= 11) return offset + 2
                if (offset <= 16) return offset + 3
                return 19
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset <= 4) return offset
                if (offset <= 9) return offset - 1
                if (offset <= 14) return offset - 2
                if (offset <= 19) return offset - 3
                return 16
            }
        }

    return TransformedText(AnnotatedString(out), creditCardOffsetTranslator)
}

private fun expiryFilter(text: AnnotatedString): TransformedText {
    val trimmed = if (text.text.length >= 4) text.text.substring(0..3) else text.text
    var out = ""
    for (i in trimmed.indices) {
        out += text[i]
        if (i % 2 == 1 && i != 3) out += "/"
    }
    val expiryOffsetTranslator =
        object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset <= 1) return offset
                return offset + 1
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset <= 1) return offset
                return offset - 1
            }
        }

    return TransformedText(AnnotatedString(out), expiryOffsetTranslator)
}
