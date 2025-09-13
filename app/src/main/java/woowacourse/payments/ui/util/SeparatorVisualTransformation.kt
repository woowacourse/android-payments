package woowacourse.payments.ui.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import kotlin.math.min

class SeparatorVisualTransformation(
    private val chunkSize: Int,
    private val separator: String,
) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val originalText = text.text
        val formattedText = originalText.chunked(chunkSize).joinToString(separator)

        val offsetMapping =
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    // offset 앞에 존재하는 구분자의 수
                    val separatorCount = (offset - 1).coerceAtLeast(0) / chunkSize
                    return offset + (separatorCount * separator.length)
                }

                override fun transformedToOriginal(offset: Int): Int {
                    val chunkGroupLength = chunkSize + separator.length

                    // 커서 위치까지의 완전한 청크그룹(청크+구분자)수
                    val chunkGroups = offset / chunkGroupLength

                    // 청크그룹 길이를 제외한 나머지 부분의 길이
                    val remainder = offset % chunkGroupLength

                    // (완전한 청크그룹의 수 * 청크 크기) + 나머지 부분의 길이
                    // 단, 나머지 부분이 청크 크기보다 길면(구분자 위에 커서가 있으면) 청크 크기로 제한
                    return chunkGroups * chunkSize + min(remainder, chunkSize)
                }
            }

        return TransformedText(AnnotatedString(formattedText), offsetMapping)
    }
}
