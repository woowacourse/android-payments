package woowacourse.payments.ui.addcard

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.domain.BankType
import woowacourse.payments.ui.model.BankTypeUiModel

class BankSelectRowTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private var clickedBank: BankType? = null

    @Before
    fun setup() {
        clickedBank = null
        composeTestRule.setContent {
            BankSelectRow(onClick = { bankType -> clickedBank = bankType })
        }
    }

    @Test
    fun `모든_은행_버튼이_표시된다`() {
        // given
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        // when
        BankType.entries.filter { it != BankType.NOT_SELECTED }.forEach {
            composeTestRule.onNodeWithText(context.getString(it.toUiModel().bankName))
                .assertIsDisplayed()
        }

        // then
    }

    @Test
    fun `은행_버튼_클릭_시_onClick이_호출된다`() {
        // given
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        // when
        composeTestRule.onNodeWithText(context.getString(BankTypeUiModel.KB.bankName)).performClick()

        // then
        assert(clickedBank == BankType.KB)
    }
}
