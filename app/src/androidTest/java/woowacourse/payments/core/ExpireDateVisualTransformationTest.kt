package woowacourse.payments.core

import androidx.compose.ui.text.AnnotatedString
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.core.ExpireDateVisualTransformation

class ExpireDateVisualTransformationTest {
    private val expireDateVisualTransformation = ExpireDateVisualTransformation(
        Card.CARD_EXPIRE_DATE_GROUP_SIZE,
        Card.CARD_EXPIRE_DATE_SEPARATOR
    )

    @Test
    fun 입력한_카드_만료일이_포맷팅_된다() {
        // given
        val input = AnnotatedString("0908")

        // when
        val transformed = expireDateVisualTransformation.filter(input)

        // then
        val expected = AnnotatedString("09 / 08").text
        assertEquals(expected, transformed.text.text)
    }
}
