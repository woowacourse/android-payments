package woowacourse.payments.ui.addcard

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.model.BankTypeUiModel

class BankSelectBtnTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private fun setupBankSelectBtn(
        bank: BankTypeUiModel = BankTypeUiModel.KB,
        onClick: () -> Unit = {},
    ) {
        composeTestRule.setContent {
            BankSelectBtn(
                bank = bank,
                onClick = onClick,
            )
        }
    }

    @Test
    fun `은행_이름이_올바르게_표시된다`() {
        // given + when
        setupBankSelectBtn(bank = BankTypeUiModel.KB)

        // then
        composeTestRule.onNodeWithText("국민카드").assertIsDisplayed()
    }

    @Test
    fun `버튼_클릭_시_onClick이_호출된다`() {
        // given
        var clicked = false
        setupBankSelectBtn(onClick = { clicked = true })

        // when
        composeTestRule.onNodeWithText("국민카드").performClick()

        // then
        assert(clicked)
    }
}
