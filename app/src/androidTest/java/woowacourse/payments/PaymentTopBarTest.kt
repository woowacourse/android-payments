package woowacourse.payments

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import junit.framework.TestCase.assertTrue
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.components.PaymentTopBar

@Suppress("ktlint:standard:function-naming")
class PaymentTopBarTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun onAddClick이_null이_아니면_추가_버튼이_표시되고_클릭이_동작한다() {
        var clicked = false

        composeTestRule.setContent {
            PaymentTopBar(onAddClick = { clicked = true })
        }

        composeTestRule
            .onNodeWithText("추가")
            .assertIsDisplayed()
            .assertHasClickAction()
            .performClick()

        composeTestRule.waitForIdle()
        assertTrue(clicked)
    }

    @Test
    fun onAddClick이_null일_때_추가_버튼이_표시되지_않는다() {
        composeTestRule.setContent {
            PaymentTopBar(onAddClick = null)
        }

        composeTestRule
            .onNodeWithText("추가")
            .assertDoesNotExist()
    }
}
