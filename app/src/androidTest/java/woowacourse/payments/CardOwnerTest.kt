package woowacourse.payments

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CardOwnerTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            LimitedTextField(
                label = "카드 소유자 이름(선택)",
                hint = "카드에 표시된 이름을 입력하세요.",
                maxLength = 30
            )
        }
    }

    @Test
    fun 카드_소유자_이름은_30자이다() {
        composeTestRule
            .onNodeWithText("")
            .performTextInput("일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십")

        composeTestRule
            .onNodeWithText("일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십")
            .assertIsDisplayed()
    }

    @Test
    fun 카드_소유자_이름은_30자를_초과할_수_없다() {
        composeTestRule
            .onNodeWithText("")
            .performTextInput("일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일")

        composeTestRule
            .onNodeWithText("일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십")
            .assertIsDisplayed()
    }
}
