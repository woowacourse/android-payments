package woowacourse.payments.ui.common

import androidx.compose.ui.text.AnnotatedString
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals

class CreditCardVisualTransformationTest {
    private lateinit var creditCardVisualTransformation: CreditCardVisualTransformation

    @Before
    fun setUp() {
        creditCardVisualTransformation = CreditCardVisualTransformation()
    }

    @Test
    fun `카드_번호를_입력하면_-가_붙은_VisualTransformation을_반환한다`() {
        // given:
        val input = AnnotatedString("1234567812345678")

        // when:
        val result = creditCardVisualTransformation.filter(input)

        // then:
        assertEquals("1234 - 5678 - 1234 - 5678", result.text.text)
    }

    @Test
    fun `16자리가_넘으면_16자리까지만_표시한다`() {
        // given:
        val input =
            androidx.compose.ui.text
                .AnnotatedString("12345678123456789000")

        // when:
        val result = creditCardVisualTransformation.filter(input)

        // then:
        assertEquals("1234 - 5678 - 1234 - 5678", result.text.text)
    }

    @Test
    fun `4자리마다_하이픈이_추가된다`() {
        // given:
        val input =
            androidx.compose.ui.text
                .AnnotatedString("123456")

        // when:
        val result = creditCardVisualTransformation.filter(input)

        // then:
        assertEquals("1234 - 56", result.text.text)
    }
}
