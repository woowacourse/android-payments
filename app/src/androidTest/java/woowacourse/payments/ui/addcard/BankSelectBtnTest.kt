package woowacourse.payments.ui.addcard

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.model.BankTypeUiModel

class BankSelectBtnTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private var clicked = false

    @Before
    fun setup() {
        clicked = false
        composeTestRule.setContent {
            BankSelectBtn(
                bank = BankTypeUiModel.KB,
                onClick = { clicked = true },
            )
        }
    }

    @Test
    fun `은행_이름이_올바르게_표시된다`() {
        // given + when
        composeTestRule.onNodeWithText("국민카드").assertIsDisplayed()

        // then
    }

    @Test
    fun `버튼_클릭_시_onClick이_호출된다`() {
        // given
        composeTestRule.onNodeWithText("국민카드").performClick()

        // when
        assert(clicked)

        // then
    }
}
