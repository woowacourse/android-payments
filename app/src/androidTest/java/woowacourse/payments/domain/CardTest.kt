package woowacourse.payments.domain

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CardTest {
    private lateinit var card: Card

    @BeforeEach
    fun setup() {
        card = Card("1111222233334444", "0421", "peto", "1234")
    }

    @Test
    fun `카드_번호_뒷자리_8글자는_마스킹되고_4자리마다_-로_구분된다`() {
        // given
        val expected = "1111 - 2222 - **** - ****"

        // when
        val actual = card.formatCardNumber()

        // then
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `만료_날짜가_구분자로_구분된다`(){
        // given
        val expected = "04 / 21"

        // when
        val actual = card.formatExpireDate()

        // then
        Assertions.assertEquals(expected, actual)
    }
}
