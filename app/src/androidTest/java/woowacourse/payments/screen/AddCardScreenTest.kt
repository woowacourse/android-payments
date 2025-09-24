package woowacourse.payments.screen

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.assertAll
import woowacourse.payments.ui.screen.addCard.AddCardScreen
import woowacourse.payments.ui.screen.addCard.AddCardStateHolder

class AddCardScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 초기화면에_텍스트필드가_보인다() {
        // given
        composeTestRule.setContent {
            AddCardScreen(
                stateHolder = AddCardStateHolder(),
                onBackPressed = {},
                onCardSaved = {},
            )
        }
        composeTestRule.onNodeWithText("BC카드").performClick()

        // then
        assertAll(
            {
                composeTestRule
                    .onNodeWithContentDescription("카드 번호 Input Field")
                    .assertIsDisplayed()
            },
            {
                composeTestRule
                    .onNodeWithContentDescription("만료일 Input Field")
                    .assertIsDisplayed()
            },
            {
                composeTestRule
                    .onNodeWithContentDescription("카드 소유자 Input Field")
                    .assertIsDisplayed()
            },
            {
                composeTestRule
                    .onNodeWithContentDescription("비밀번호 Input Field")
                    .assertIsDisplayed()
            },
        )
    }

    @Test
    fun 잘못된_입력시_에러메시지가_보인다() {
        // given
        composeTestRule.setContent {
            AddCardScreen(
                stateHolder = AddCardStateHolder(),
                onBackPressed = {},
                onCardSaved = {},
            )
        }
        composeTestRule.onNodeWithText("BC카드").performClick()
        composeTestRule.waitForIdle()

        // when
        composeTestRule
            .onNodeWithContentDescription("카드 번호 Input Field")
            .performClick()
            .performTextInput("1234")

        composeTestRule
            .onNodeWithContentDescription("만료일 Input Field")
            .performClick()
            .performTextInput("11")
        composeTestRule
            .onNodeWithContentDescription("카드 소유자 Input Field")
            .performClick()
            .performTextInput("11")
        composeTestRule
            .onNodeWithContentDescription("비밀번호 Input Field")
            .performClick()
            .performTextInput("12")
        composeTestRule.onNodeWithContentDescription("완료").performClick()

        // then
        assertAll(
            {
                composeTestRule
                    .onNodeWithContentDescription("카드 번호 Input Field Error")
                    .assertIsDisplayed()
            },
            {
                composeTestRule
                    .onNodeWithContentDescription("만료일 Input Field Error")
                    .assertIsDisplayed()
            },
            {
                composeTestRule
                    .onNodeWithContentDescription("카드 소유자 Input Field Error")
                    .assertIsDisplayed()
            },
            {
                composeTestRule
                    .onNodeWithContentDescription("비밀번호 Input Field Error")
                    .assertIsDisplayed()
            },
        )
    }
}
