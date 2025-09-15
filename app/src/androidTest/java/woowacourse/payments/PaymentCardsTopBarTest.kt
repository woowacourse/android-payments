package woowacourse.payments

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.component.PaymentCardsTopBar

class PaymentCardsTopBarTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun 카드가_2개_미만이면_추가버튼이_보이지_않는다() {
        composeRule.setContent {
            PaymentCardsTopBar(
                showAddButton = false,
                modifier = Modifier.testTag(Tags.TOPBAR_ADD),
                onAddClick = {},
            )
        }

        composeRule.onNodeWithText("추가").assertDoesNotExist()
    }

    @Test
    fun 카드가_2개_이상이면_추가버튼이_보인다() {
        composeRule.setContent {
            PaymentCardsTopBar(
                showAddButton = true,
                modifier = Modifier.testTag(Tags.TOPBAR_ADD),
                onAddClick = {},
            )
        }

        composeRule.onNodeWithText("추가").assertIsDisplayed()
    }

    @Test
    fun 추가버튼을_클릭하면_onAddClick이_호출된다() {
        var clicked = false

        composeRule.setContent {
            PaymentCardsTopBar(
                showAddButton = true,
                modifier = Modifier.testTag(Tags.TOPBAR_ADD),
                onAddClick = { clicked = true },
            )
        }

        // when
        composeRule.onNodeWithText("추가").performClick()

        // then
        assertTrue(clicked)
    }
}
