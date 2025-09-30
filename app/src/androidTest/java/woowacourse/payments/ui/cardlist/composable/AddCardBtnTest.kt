package woowacourse.payments.ui.cardlist.composable

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class AddCardBtnTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private fun setupAddCardBtn(onClick: () -> Unit = {}) {
        composeTestRule.setContent {
            AddCardBtn(onClick = onClick)
        }
    }

    @Test
    fun `더하기_기호가_표시된다`() {
        // given + when
        setupAddCardBtn()

        // then
        composeTestRule.onNodeWithText("+").assertIsDisplayed()
    }

    @Test
    fun `버튼_클릭_시_onClick이_호출된다`() {
        // given
        var clicked = false
        setupAddCardBtn(onClick = { clicked = true })

        // when
        composeTestRule.onNodeWithText("+").performClick()

        // then
        assert(clicked)
    }
}
