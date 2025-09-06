package woowacourse.payments.ui.common

import androidx.compose.ui.text.AnnotatedString
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals

class DateVisualTransformationTest {
    private lateinit var dateVisualTransformation: DateVisualTransformation

    @Before
    fun setUp() {
        dateVisualTransformation = DateVisualTransformation()
    }

    @Test
    fun `4자리 입력 시 슬래시가 가운데 삽입된다`() {
        // given:
        val input = AnnotatedString("0201")

        // when:
        val result = dateVisualTransformation.filter(input)

        // then:
        assertEquals("02 / 01", result.text.text)
    }

    @Test
    fun `4자리 초과 입력 시 잘린 후 슬래시가 삽입된다`() {
        // given:
        val input = AnnotatedString("020123")

        // when:
        val result = dateVisualTransformation.filter(input)

        // then:
        assertEquals("02 / 01", result.text.text)
    }
}
