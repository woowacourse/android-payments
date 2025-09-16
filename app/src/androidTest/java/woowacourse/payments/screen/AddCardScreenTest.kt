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

class AddCardScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 초기화면에_텍스트필드가_보인다() {
        // given
        composeTestRule.setContent {
            AddCardScreen(
                onBackPressed = {},
                onCardSaved = {},
            )
        }
        composeTestRule.onNodeWithText("BC카드").performClick()

        // then
        assertAll(
            {
                composeTestRule
                    .onNodeWithContentDescription("Card Number Input Field")
                    .assertIsDisplayed()
            },
            {
                composeTestRule
                    .onNodeWithContentDescription("Expired Input Field")
                    .assertIsDisplayed()
            },
            {
                composeTestRule
                    .onNodeWithContentDescription("Card Owner Input Field")
                    .assertIsDisplayed()
            },
            {
                composeTestRule
                    .onNodeWithContentDescription("Password Input Field")
                    .assertIsDisplayed()
            },
        )
    }

    @Test
    fun 잘못된_입력시_에러메시지가_보인다() {
        // given
        composeTestRule.setContent {
            AddCardScreen(
                onBackPressed = {},
                onCardSaved = {},
            )
        }

        // when
        composeTestRule
            .onNodeWithContentDescription("Card Number Input Field")
            .performTextInput("1234")
        composeTestRule
            .onNodeWithContentDescription("Expired Input Field")
            .performTextInput("11")
        composeTestRule
            .onNodeWithContentDescription("Card Owner Input Field")
            .performTextInput("11")
        composeTestRule
            .onNodeWithContentDescription("Password Input Field")
            .performTextInput("12")
        composeTestRule.onNodeWithContentDescription("완료").performClick()

        // then
        assertAll(
            {
                composeTestRule
                    .onNodeWithContentDescription("Card Number Input Error")
                    .assertIsDisplayed()
            },
            {
                composeTestRule
                    .onNodeWithContentDescription("Expired Input Error")
                    .assertIsDisplayed()
            },
            {
                composeTestRule
                    .onNodeWithContentDescription("Card Owner Input Error")
                    .assertIsDisplayed()
            },
            {
                composeTestRule
                    .onNodeWithContentDescription("Password Input Error")
                    .assertIsDisplayed()
            },
        )
    }
}
