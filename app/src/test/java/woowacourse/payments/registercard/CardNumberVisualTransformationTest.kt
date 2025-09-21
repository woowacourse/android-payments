package woowacourse.payments.registercard

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.VisualTransformation
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.payments.ui.registercard.CardNumberVisualTransformation

class CardNumberVisualTransformationTest {
    private lateinit var visualTransformation: VisualTransformation

    @BeforeEach
    fun setUp() {
        visualTransformation = CardNumberVisualTransformation()
    }

    @Test
    fun `카드번호는 4자리마다 구분자가 붙는다`() {
        // given

        // when
        val transformedText = visualTransformation.filter(AnnotatedString("12345678"))

        // then
        val result = transformedText.text.text
        val expected = "1234-5678"
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun `카드번호 4자리마다 삽입된 구분자를 고려하여 커서 위치를 올바르게 변환한다`() {
        // given
        val transformedText = visualTransformation.filter(AnnotatedString("12345678"))

        // when & then
        val result = transformedText.offsetMapping.originalToTransformed(7)
        val expected = 8
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun `카드번호는 구분자가 포함된 변환 텍스트에서 커서 위치를 원본 텍스트 위치로 변환한다`() {
        // given
        val transformedText = visualTransformation.filter(AnnotatedString("12345678"))

        // when & then
        val result = transformedText.offsetMapping.transformedToOriginal(8)
        val expected = 7
        Assertions.assertEquals(expected, result)
    }
}
