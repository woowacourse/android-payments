package woowacourse.payments.core

import androidx.compose.ui.text.AnnotatedString
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ExpireDateVisualTransformationTest {
    private val expireDateVisualTransformation = ExpireDateVisualTransformation(2, "/")

    @Test
    fun 입력한_카드_만료일이_포맷팅_된다() {
        val input = AnnotatedString("0908")
        val transformed = expireDateVisualTransformation.filter(input)

        val expected = AnnotatedString("09 / 08").text

        assertEquals(expected, transformed.text.text)
    }
}
