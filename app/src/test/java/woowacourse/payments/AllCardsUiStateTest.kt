package woowacourse.payments

import androidx.compose.runtime.mutableStateListOf
import org.junit.jupiter.api.Test
import woowacourse.payments.ui.allcards.model.AllCardsUiState
import woowacourse.payments.ui.uimodel.CardInfoUiState

class AllCardsUiStateTest {
    @Test
    fun `카드가_없을_때_빈_뷰_타입을_반환한다`() {
        // given
        AllCardsUiState(
            mutableStateListOf(),
        )

        // when
        val result =
            AllCardsUiState(
                mutableStateListOf(),
            ).viewType

        // then
        assert(result == AllCardsUiState.ViewType.EMPTY)
    }

    @Test
    fun `카드가 하나일 떄 Single 뷰 타입을 반환한다`() {
        // given
        AllCardsUiState(
            mutableStateListOf(
                CardInfoUiState(),
            ),
        )

        // when
        val result =
            AllCardsUiState(
                mutableStateListOf(
                    CardInfoUiState(),
                ),
            ).viewType

        // then
        assert(result == AllCardsUiState.ViewType.SINGLE)
    }

    @Test
    fun `카드가 두 개 이상일 때 Multiple 뷰 타입을 반환한다 `() {
        // given
        AllCardsUiState(
            mutableStateListOf(
                CardInfoUiState(),
                CardInfoUiState(),
            ),
        )

        // when
        val result =
            AllCardsUiState(
                mutableStateListOf(
                    CardInfoUiState(),
                    CardInfoUiState(),
                ),
            ).viewType

        // then
        assert(result == AllCardsUiState.ViewType.MULTIPLE)
    }
}
