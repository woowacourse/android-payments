package woowacourse.payments.ui.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class CardNumberVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val formattedText = text.text.chunked(CHUNK_SIZE).joinToString(SEPARATOR)

        val offsetMapping =
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    if (offset <= CHUNK_SIZE) return offset
                    if (offset <= CHUNK_SIZE * 2) return offset + SEPARATOR.length
                    if (offset <= CHUNK_SIZE * 3) return offset + SEPARATOR.length * 2
                    return offset + SEPARATOR.length * 3
                }

                override fun transformedToOriginal(offset: Int): Int {
                    // 1번 덩어리 내부 (0~4)
                    if (offset <= CHUNK_SIZE) return offset
                    // 1번 구분자 내부 (5~7) -> 4로 스냅
                    if (offset <= CHUNK_SIZE + SEPARATOR.length) return CHUNK_SIZE
                    // 2번 덩어리 내부 (8~11) -> 구분자 1개만큼 빼줌
                    if (offset <= CHUNK_SIZE * 2 + SEPARATOR.length) return offset - SEPARATOR.length
                    // 2번 구분자 내부 (12~14) -> 11로 스냅
                    if (offset <= CHUNK_SIZE * 2 + SEPARATOR.length * 2) return CHUNK_SIZE * 2
                    // 3번 덩어리 내부 (15~18) -> 구분자 2개만큼 빼줌
                    if (offset <= CHUNK_SIZE * 3 + SEPARATOR.length * 2) return offset - SEPARATOR.length * 2
                    // 3번 구분자 내부 (19~21) -> 18로 스냅
                    if (offset <= CHUNK_SIZE * 3 + SEPARATOR.length * 3) return CHUNK_SIZE * 3
                    // 4번 덩어리 내부 (22~25) -> 구분자 3개만큼 빼줌
                    return offset - SEPARATOR.length * 3
                }
            }

        return TransformedText(AnnotatedString(formattedText), offsetMapping)
    }

    companion object {
        private const val SEPARATOR = " - "
        private const val CHUNK_SIZE = 4
    }
}
