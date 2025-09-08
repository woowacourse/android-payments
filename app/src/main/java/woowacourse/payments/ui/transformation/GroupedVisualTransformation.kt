package woowacourse.payments.ui.transformation

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

/**
 * groupSizes: 그룹 길이 리스트 (예: 카드번호 -> listOf(4,4,4,4), 만료일 -> listOf(2,2))
 * separator: 그룹 사이에 넣을 구분자 (예: " / ", " ", "-")
 */
class GroupedVisualTransformation(
    private val groupSizes: List<Int>,
    private val separator: String = " ",
) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val rawText: String = text.text
        val transformedText: String =
            buildString {
                var index = 0
                groupSizes.forEachIndexed { i: Int, size: Int ->
                    val endIndex = (index + size).coerceAtMost(rawText.length)
                    append(rawText.substring(index, endIndex))
                    if (endIndex < rawText.length && i < groupSizes.lastIndex) {
                        append(separator)
                    }
                    index = endIndex
                }
            }

        val boundaries: List<Int> = groupSizes.runningReduce(Int::plus)
        val offsetMapping =
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    boundaries.forEachIndexed { index: Int, boundary: Int ->
                        if (offset <= boundary) {
                            return offset + index * separator.length
                        }
                    }
                    return transformedText.length
                }

                override fun transformedToOriginal(offset: Int): Int {
                    boundaries.forEachIndexed { index: Int, boundary: Int ->
                        val transformedBoundary: Int = boundary + index * separator.length
                        if (offset <= transformedBoundary) {
                            return (offset - index * separator.length).coerceAtMost(boundary)
                        }
                    }
                    return rawText.length
                }
            }

        return TransformedText(
            androidx.compose.ui.text
                .AnnotatedString(transformedText),
            offsetMapping,
        )
    }
}
