package woowacourse.payments.ui.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.model.BankTypeUiModel

class BankSelectorBottomSheetTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `카드사를_선택하면_선택된_카드사가_전달된다`() {
        // given
        var selectedBank: BankTypeUiModel? = null

        composeTestRule.setContent {
            BankSelectBottomSheet(
                onBankSelected = { selectedBank = it },
                onDismissRequest = {},
            )
        }

        // when
        val target = composeTestRule.onNodeWithText("국민카드")
        target.assertIsDisplayed()
        target.performClick()

        // then
        composeTestRule.waitForIdle()

        assert(selectedBank == BankTypeUiModel.KB)
    }

    @Test
    fun `카드사를_선택하면_닫기가_전달된다`() {
        // given
        var dismissed = false

        composeTestRule.setContent {
            BankSelectBottomSheet(
                onBankSelected = {},
                onDismissRequest = { dismissed = true },
            )
        }

        // when
        val target = composeTestRule.onNodeWithText("국민카드")
        target.assertIsDisplayed()
        target.performClick()

        // then
        composeTestRule.waitForIdle()

        assert(dismissed)
    }
}
