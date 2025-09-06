package woowacourse.payments.core

import androidx.compose.ui.text.AnnotatedString
import org.junit.jupiter.api.Assertions
import org.junit.Test

class CardNumberVisualTransformationTest {
    private val transformation = CardNumberVisualTransformation(
        maxLength = 16,
    )

    @Test
    fun 입력한_카드번호가_구분자에_따라_포맷팅된다() {
        // given
        val input = AnnotatedString("1234123412341234")
        val transformed = transformation.filter(input)

        // when
        val excepted = AnnotatedString("1234 - 1234 - 1234 - 1234").text

        // then
        Assertions.assertEquals(excepted, transformed.text.text)
    }
}
