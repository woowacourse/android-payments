package woowacourse.payments.ui.addcard

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.domain.BankType
import woowacourse.payments.ui.model.BankTypeUiModel

class BankSelectRowTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private fun setupBankSelectRow(onClick: (BankType) -> Unit = {}) {
        composeTestRule.setContent {
            BankSelectRow(onClick = onClick)
        }
    }

    @Test
    fun `모든_은행_버튼이_표시된다`() {
        // given + when
        setupBankSelectRow()
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        // then
        BankType.entries.filter { it != BankType.NOT_SELECTED }.forEach {
            composeTestRule.onNodeWithText(context.getString(it.toUiModel().bankName)).assertIsDisplayed()
        }
    }

    @Test
    fun `은행_버튼_클릭_시_onClick이_호출된다`() {
        // given
        var clickedBank: BankType? = null
        setupBankSelectRow(onClick = { bankType -> clickedBank = bankType })
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        // when
        composeTestRule.onNodeWithText(context.getString(BankTypeUiModel.KB.bankName)).performClick()

        // then
        assert(clickedBank == BankType.KB)
    }
}
