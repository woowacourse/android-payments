package woowacourse.payments.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.component.PaymentToolbar

class PaymentToolbarTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `카드_목록_상단엔_Payments_텍스트가_보인다`() {
        // given
        val title = "Payments"

        // when
        composeTestRule.setContent {
            PaymentToolbar({}, false)
        }

        // then
        composeTestRule.onNodeWithText(title).assertIsDisplayed()
    }

    @Test
    fun `카드가_2개_초과면_추가_버튼이_보인다`() {
        // given
        val addButtonVisible = true

        // when
        composeTestRule.setContent {
            PaymentToolbar({}, addButtonVisible)
        }

        // then
        composeTestRule.onNodeWithText("추가").assertIsDisplayed()
    }
}
