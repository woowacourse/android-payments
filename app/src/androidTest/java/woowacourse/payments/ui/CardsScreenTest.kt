package woowacourse.payments.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class CardsScreenTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun `카드_목록이_비어있을_때에는_새_카드를_등록하라는_안내가_노출된다`() {
        // given
        composeRule.setContent {
            CardsScreen(
                state =
                    CardsUiState(
                        emptyList(),
                    ),
            )
        }

        // then
        composeRule
            .onNodeWithText("새로운 카드를 등록해주세요")
            .assertIsDisplayed()
    }

    @Test
    fun `카드_목록에_카드가_한_개_있을_때_카드_추가_UI가_노출된다`() {
        // given
        composeRule.setContent {
            CardsScreen(
                state =
                    CardsUiState(
                        listOf(
                            Card(
                                number = "1234".repeat(4),
                                owner = "CREW",
                                expiredDate = "0421",
                            ),
                        ),
                    ),
            )
        }

        // then
        composeRule
            .onNodeWithContentDescription("새 카드 등록 버튼")
            .assertIsDisplayed()
    }

    @Test
    fun `카드_목록에_카드가_여러_개_있을_때_카드_추가_UI가_노출된다`() {
        // given
        composeRule.setContent {
            CardsScreen(
                CardsUiState(
                    listOf(
                        Card(
                            number = "1234".repeat(4),
                            owner = "CREW",
                            expiredDate = "0421",
                        ),
                        Card(
                            number = "1234".repeat(4),
                            owner = "CREW",
                            expiredDate = "0421",
                        ),
                        Card(
                            number = "1234".repeat(4),
                            owner = "CREW",
                            expiredDate = "0421",
                        ),
                    ),
                ),
            )
        }

        // then
        composeRule.onNodeWithContentDescription("새 카드 등록 버튼")
    }
}
