package woowacourse.payments.registercard

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.VisualTransformation
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.payments.ui.registercard.ExpiryDateVisualTransformation

class ExpiryDateVisualTransformationTest {
    private lateinit var visualTransformation: VisualTransformation

    @BeforeEach
    fun setUp() {
        visualTransformation = ExpiryDateVisualTransformation()
    }

    @Test
    fun `만료일은 2자리마다 구분자가 붙는다`() {
        // given

        // when
        val transformationText = visualTransformation.filter(AnnotatedString("1111"))

        // then
        val result = transformationText.text.text
        val expected = "11/11"
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun `만료일 2자리마다 삽입된 구분자을 고려하여 커서 위치를 올바르게 변환한다`() {
        // given
        val transformedText = visualTransformation.filter(AnnotatedString("1111"))

        // when & then
        val result = transformedText.offsetMapping.originalToTransformed(4)
        val expected = 5
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun `만료일은 구분자가 포함된 변환 텍스트에서 커서 위치를 원본 텍스트 위치로 변환한다`() {
        // given
        val transformedText = visualTransformation.filter(AnnotatedString("1111"))

        // when & then
        val result = transformedText.offsetMapping.transformedToOriginal(5)
        val expected = 4
        Assertions.assertEquals(expected, result)
    }
}
