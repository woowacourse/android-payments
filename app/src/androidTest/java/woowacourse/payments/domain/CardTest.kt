package woowacourse.payments.domain

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CardTest {
    private lateinit var card: Card

    @Before
    fun setup() {
        card = Card("1111222233334444", "0421", "peto", "1234")
    }

    @Test
    fun `카드_번호_뒷자리_8글자는_마스킹되고_4자리마다_-로_구분된다`() {
        // given
        val expected = "1111 - 2222 - **** - ****"

        // when
        val actual = card.formatCardNumber(
            groupSize = 4,
            separator = " - ",
            cardMaskChar = "*",
        )

        // then
        assertEquals(expected, actual)
    }

    @Test
    fun `만료_날짜가_구분자로_구분된다`(){
        // given
        val expected = "04 / 21"

        // when
        val actual = card.formatExpireDate(
            groupSize = 2,
            separator = " / "
        )

        // then
        assertEquals(expected, actual)
    }
}
