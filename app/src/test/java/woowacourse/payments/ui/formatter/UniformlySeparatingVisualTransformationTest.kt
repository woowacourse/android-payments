package woowacourse.payments.ui.formatter

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TransformedText
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

@Suppress("ktlint:standard:function-naming")
class UniformlySeparatingVisualTransformationTest {
    @Test
    fun `정해진 크기와 구분자로 나눠진 문자열이 반환된다`() {
        // given
        val originalText = "AAAABBBBCCCCDDDD"
        val visualTransformation = UniformlySeparatingVisualTransformation(4, " | ")

        // when
        val transformedText: TransformedText =
            visualTransformation.filter(AnnotatedString(originalText))
        val actual: String = transformedText.text.text

        // then
        val expected = "AAAA | BBBB | CCCC | DDDD"
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `원본 문자열의 커서 위치를 변환된 문자열의 커서 위치 매핑한다 1`() {
        // given
        val text = "AAAABBBBCCCCDDDD"
        val originalIndex = 2
        val visualTransformation = UniformlySeparatingVisualTransformation(4, " | ")

        // when
        val transformedText: TransformedText =
            visualTransformation.filter(AnnotatedString(text))
        val actual: Int = transformedText.offsetMapping.originalToTransformed(originalIndex)

        // then
        val expected = 2
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `원본 문자열의 커서 위치를 변환된 문자열의 커서 위치로 매핑한다 2`() {
        // given
        val text = "AAAABBBBCCCCDDDD"
        val originalIndex = 5
        val visualTransformation = UniformlySeparatingVisualTransformation(4, " | ")

        // when
        val transformedText: TransformedText =
            visualTransformation.filter(
                AnnotatedString(text),
            )
        val actual: Int = transformedText.offsetMapping.originalToTransformed(originalIndex)

        // then
        val expected = 8
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `변환된 문자열의 커서 위치를 원본 문자열의 커서 위치로 매핑한다 1`() {
        // given
        val text = "AAAABBBBCCCCDDDD"
        val originalIndex = 2
        val visualTransformation = UniformlySeparatingVisualTransformation(4, " | ")

        // when
        val transformedText: TransformedText =
            visualTransformation.filter(
                AnnotatedString(text),
            )
        val actual: Int = transformedText.offsetMapping.transformedToOriginal(originalIndex)

        // then
        val expected = 2
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `변환된 문자열의 커서 위치를 원본 문자열의 커서 위치로 매핑한다 2`() {
        // given
        val text = "AAAABBBBCCCCDDDD"
        val originalIndex = 15
        val visualTransformation = UniformlySeparatingVisualTransformation(4, " | ")

        // when
        val transformedText: TransformedText =
            visualTransformation.filter(
                AnnotatedString(text),
            )
        val actual: Int = transformedText.offsetMapping.transformedToOriginal(originalIndex)

        // then
        val expected = 9
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}
