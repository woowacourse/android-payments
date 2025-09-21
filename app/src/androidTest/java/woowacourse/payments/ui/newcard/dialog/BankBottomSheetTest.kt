package woowacourse.payments.ui.newcard.dialog

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import woowacourse.payments.R
import woowacourse.payments.domain.model.Bank
import woowacourse.payments.domain.model.BankType

class BankBottomSheetTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun `은행_목록이_뜬다`() {
        // given & when
        composeTestRule.setContent {
            BankBottomSheet(
                sheetState = rememberModalBottomSheetState(),
                banks =
                    listOf(
                        Bank(BankType.SHINHAN, R.drawable.ic_shinhan),
                        Bank(BankType.KB, R.drawable.ic_kb),
                    ),
                onClick = {},
                onDismiss = {},
            )
        }

        // then
        composeTestRule.onNodeWithText("신한카드").assertIsDisplayed()
        composeTestRule.onNodeWithText("국민카드").assertIsDisplayed()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun `은행을_클릭하면_onClick함수가_실행된다`() {
        // given
        val clickedBank = mutableStateOf<BankType?>(null)

        composeTestRule.setContent {
            BankBottomSheet(
                sheetState = rememberModalBottomSheetState(),
                banks =
                    listOf(
                        Bank(BankType.SHINHAN, R.drawable.ic_shinhan),
                        Bank(BankType.KB, R.drawable.ic_kb),
                    ),
                onClick = { bank -> clickedBank.value = bank.type },
                onDismiss = {},
            )
        }

        // when
        composeTestRule.onNodeWithText("신한카드").performClick()

        // then
        assertEquals(BankType.SHINHAN, clickedBank.value)
    }
}
