package woowacourse.payments.ui.component

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class CardNumberVisualTransformation(
    private val groupSize: Int = 4,
    private val delimiter: String = "-",
) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val digits = text.text.filter { it.isDigit() }.take(16)
        val formatted = digits.chunked(groupSize).joinToString(delimiter)
        val offsetMapping =
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    // offset(원본 숫자 위치)까지 delimiter가 몇 개 들어가는지
                    val groupsBeforeCursor =
                        (offset.coerceAtMost(digits.length) / groupSize)
                            .coerceAtMost((digits.length - 1) / groupSize)
                    return offset + groupsBeforeCursor * delimiter.length
                }

                override fun transformedToOriginal(offset: Int): Int {
                    // 화면 offset에서 delimiter가 몇 개 들어가는지
                    val groupsBeforeCursor = (offset / (groupSize + delimiter.length))
                    val original = offset - groupsBeforeCursor * delimiter.length
                    return original.coerceIn(0, digits.length)
                }
            }

        return TransformedText(AnnotatedString(formatted), offsetMapping)
    }
}
