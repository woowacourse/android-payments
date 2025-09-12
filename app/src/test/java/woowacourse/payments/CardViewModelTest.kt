package woowacourse.payments

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import woowacourse.payments.ui.catalog.CardUiState
import woowacourse.payments.ui.catalog.CardViewModel
import woowacourse.payments.ui.model.PaymentCardUiModel

class CardViewModelTest {
    @Test
    fun `처음 기본 상태는 Empty이다`() {
        // given
        val cardViewModel = CardViewModel()

        // when
        val actual = cardViewModel.cardUiState
        val expected = CardUiState.Empty

        // then
        actual shouldBe expected
    }

    @Test
    fun `기본 상태에서 카드를 하나 등록하면 Single이다`() {
        // given
        val cardViewModel = CardViewModel()
        val newCard =
            PaymentCardUiModel(
                number = "1234123412341234",
                expirationDate = "1234",
                cardholderName = "CREW",
            )

        // when
        cardViewModel.addCard(newCard)
        val actual = cardViewModel.cardUiState
        val expected = CardUiState.Single(newCard)

        // then
        actual shouldBe expected
    }

    @Test
    fun `Single에서 카드를 하나 더 등록하면 Multiple이다`() {
        // given
        val newCard =
            PaymentCardUiModel(
                number = "1234123412341234",
                expirationDate = "1234",
                cardholderName = "CREW",
            )

        val anotherNewCard =
            PaymentCardUiModel(
                number = "4321432143214321",
                expirationDate = "1234",
                cardholderName = "CREW",
            )

        val cardViewModel = CardViewModel()
        cardViewModel.addCard(newCard)

        // when
        cardViewModel.addCard(anotherNewCard)
        val actual = cardViewModel.cardUiState
        val expected = CardUiState.Multiple(listOf(newCard, anotherNewCard))

        // then
        actual shouldBe expected
    }

    @Test
    fun `Multiple에서 카드를 하나 더 등록하면 Multiple이다`() {
        // given
        val oneNewCard =
            PaymentCardUiModel(
                number = "1234123412341234",
                expirationDate = "1234",
                cardholderName = "CREW",
            )

        val twoNewCard =
            PaymentCardUiModel(
                number = "4321432143214321",
                expirationDate = "1234",
                cardholderName = "CREW",
            )

        val threeNewCard =
            PaymentCardUiModel(
                number = "4321432143214322",
                expirationDate = "1234",
                cardholderName = "CREW",
            )

        val cardViewModel = CardViewModel()
        cardViewModel.addCard(oneNewCard)
        cardViewModel.addCard(twoNewCard)

        // when
        cardViewModel.addCard(threeNewCard)
        val actual = cardViewModel.cardUiState
        val expected = CardUiState.Multiple(listOf(oneNewCard, twoNewCard, threeNewCard))

        // then
        actual shouldBe expected
    }
}
