package woowacourse.payments.ui.screen.cardAddition.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.click
import androidx.compose.ui.test.isRoot
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.model.IssuingBank

class BankSelectBottomSheetTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun `외부_영역을_터치해도_사라지지_않는다`() {
        // given
        var dismissCalled = false
        composeRule.setContent {
            BankSelectBottomSheet(
                onBankSelected = {},
                onDismissRequest = { dismissCalled = true },
            )
        }

        // when
        composeRule
            .onAllNodes(isRoot())[0]
            .performTouchInput {
                click(center)
            }

        // then
        assertFalse(dismissCalled)
        composeRule
            .onNodeWithContentDescription("카드사 선택창")
            .assertIsDisplayed()
    }

    @Test
    fun `카드사를_선택하면_선택창이_사라지고_카드사가_저장된다`() {
        // given
        var selectedBank: IssuingBank = IssuingBank.NOT_SELECTED
        var dismissed = false
        composeRule.setContent {
            BankSelectBottomSheet(
                onBankSelected = { newBank -> selectedBank = newBank },
                onDismissRequest = { dismissed = true },
            )
        }

        // when
        composeRule
            .onNodeWithText("카카오뱅크")
            .performClick()
        composeRule.waitForIdle()

        // then
        assertEquals(IssuingBank.KAKAO, selectedBank)
        assertTrue(dismissed)
    }
}
