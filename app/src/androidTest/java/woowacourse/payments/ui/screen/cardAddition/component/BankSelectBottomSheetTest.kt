package woowacourse.payments.ui.screen.cardAddition.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.click
import androidx.compose.ui.test.isRoot
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.model.IssuingBank

class BankSelectBottomSheetTest {
    @get:Rule
    val composeRule = createComposeRule()

    private lateinit var selectedBank: IssuingBank

    @OptIn(ExperimentalMaterial3Api::class)
    @Before
    fun setUp() {
        composeRule.setContent {
            val sheetState = rememberModalBottomSheetState { false }
            BankSelectBottomSheet(
                sheetState = sheetState,
                onBankSelected = { selectedBank = it },
            )
        }
    }

    @Test
    fun `외부_영역을_터치해도_사라지지_않는다`() {
        // when
        composeRule
            .onAllNodes(isRoot())[0]
            .performTouchInput {
                click(center)
            }

        // then
        composeRule
            .onNodeWithContentDescription("카드사 선택창")
            .assertIsDisplayed()
    }

    @Test
    fun `카드사를_선택하면_카드사가_저장된다`() {
        // when
        composeRule
            .onNodeWithText("카카오뱅크")
            .performClick()
        composeRule.waitForIdle()

        // then
        assertEquals(IssuingBank.KAKAO, selectedBank)
    }
}
