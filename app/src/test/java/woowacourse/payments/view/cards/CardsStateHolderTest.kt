package woowacourse.payments.view.cards

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.payments.domain.BankType
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.CardPassword
import woowacourse.payments.domain.CardsRepository
import java.time.YearMonth

class CardsStateHolderTest {
    @Test
    fun `카드가_수정되면_카드_목록_화면에_변경사항이_반영된다`() {
        // given
        val cards: MutableList<Card> = mutableListOf()

        val repository =
            object : CardsRepository {
                override val cards: List<Card> = cards

                override fun addCard(card: Card) {
                    cards.add(card)
                }

                override fun editCard(
                    old: Card,
                    new: Card,
                ) {
                    if (!cards.remove(old)) return

                    cards.add(new)
                }
            }

        val stateHolder = CardsStateHolder(repository = repository)

        val old: CardsUiState = stateHolder.uiState

        // when
        val newCard =
            Card(
                bankType = BankType.BC,
                number = CardNumber("1234".repeat(4)),
                expiredDate = YearMonth.of(2030, 10),
                password = CardPassword("1234"),
                holder = "CREW",
            )

        repository.addCard(newCard)
        stateHolder.fetchCards()

        // then
        val new: CardsUiState = stateHolder.uiState
        assertThat(new).isNotEqualTo(old)
    }
}
