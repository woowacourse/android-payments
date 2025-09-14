package woowacourse.payments

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.list.CardListScreen
import woowacourse.payments.list.CardUiModel
import woowacourse.payments.newCard.CardScreenUiState

@Suppress("ktlint:standard:function-naming")
class AddTwoOrMoreCardListTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            CardListScreen(
                cards =
                    CardScreenUiState.from(
                        listOf(
                            CardUiModel(
                                "1234 - 1234 - **** - ****",
                                "12 / 34",
                                "9999",
                                "PARK JIWON",
                            ),
                            CardUiModel(
                                "7890 - 7890 - **** - ****",
                                "09 / 99",
                                "1234",
                                "TOMATO BASIL ADE",
                            ),
                        ),
                    ),
                onAddClick = {},
            )
        }
    }

    @Test
    fun 추가된_카드가_두_개_이상이면_카드_추가_안내가_안_보인다() {
        composeTestRule
            .onNodeWithText("새로운 카드를 등록해주세요")
            .assertDoesNotExist()
    }

    @Test
    fun 추가된_카드가_두_개_이상이면_탑바에_추가_버튼이_보인다() {
        composeTestRule
            .onNodeWithText("추가")
            .assertIsDisplayed()
    }

    @Test
    fun 추가된_카드가_두_개_이상이면_추가된_카드들의_일부가_마스킹된_번호가_보인다() {
        composeTestRule
            .onNodeWithText("1234 - 1234 - **** - ****")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("7890 - 7890 - **** - ****")
            .assertIsDisplayed()
    }

    @Test
    fun 추가된_카드가_두_개_이상이면_추가된_카드들의_만료일이_보인다() {
        composeTestRule
            .onNodeWithText("12 / 34")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("09 / 99")
            .assertIsDisplayed()
    }

    @Test
    fun 추가된_카드가_두_개_이상이면_추가된_카드들의_소유자명이_보인다() {
        composeTestRule
            .onNodeWithText("PARK JIWON")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("TOMATO BASIL ADE")
            .assertIsDisplayed()
    }

    @Test
    fun 추가된_카드가_두_개_이상이면_카드_추가_뷰가_안_보인다() {
        composeTestRule
            .onNode(hasContentDescription("새로운 카드 추가"))
            .assertDoesNotExist()
    }
}
