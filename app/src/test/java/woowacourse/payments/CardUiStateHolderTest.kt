package woowacourse.payments

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import woowacourse.payments.ui.catalog.CardUiState
import woowacourse.payments.ui.catalog.CardUiStateHolder

class CardUiStateHolderTest {
    @Test
    fun `처음 기본 상태는 Empty이다`() {
        // given
        val cardUiStateHolder = CardUiStateHolder()

        // when
        val actual = cardUiStateHolder.cardUiState
        val expected = CardUiState.EMPTY

        // then
        actual shouldBe expected
    }

    @Test
    fun `기본 상테에서 카드를 하나 등록하면 Single이다`() {
        // given
        val cardUiStateHolder = CardUiStateHolder()

        // when
        cardUiStateHolder.addCard()
        val actual = cardUiStateHolder.cardUiState
        val expected = CardUiState.SINGLE

        // then
        actual shouldBe expected
    }

    @Test
    fun `Single에서 카드를 하나 더 등록하면 Multiple이다`() {
        // given
        val cardUiStateHolder = CardUiStateHolder()
        cardUiStateHolder.addCard()

        // when
        cardUiStateHolder.addCard()
        val actual = cardUiStateHolder.cardUiState
        val expected = CardUiState.MUTIPLE

        // then
        actual shouldBe expected
    }
}