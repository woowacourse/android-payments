package woowacourse.payments.ui.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.assertAll
import woowacourse.payments.ui.model.BankTypeUiModel

class BankSelectorBottomSheetTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `카드사를_선택하면_선택된_카드사와_닫기를_전달한다`() {
        // given
        var selectedBank: BankTypeUiModel? = null
        var dismissed = false

        composeTestRule.setContent {
            BankSelectBottomSheet(
                onBankSelected = { selectedBank = it },
                onDismiss = { dismissed = true },
            )
        }

        // when
        composeTestRule
            .onNodeWithText("국민카드")
            .assertIsDisplayed()
            .performClick()

        // then
        composeTestRule.waitForIdle()

        assertAll(
            { assert(selectedBank == BankTypeUiModel.KB) },
            { assert(dismissed) },
        )
    }
}
