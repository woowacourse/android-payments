package woowacourse.payments.ui.newcard.dialog

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.R
import woowacourse.payments.domain.model.Bank
import woowacourse.payments.domain.model.BankType

class BankBottomSheetTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun `은행_목록이_뜬다`() {
        composeTestRule.setContent {
            BankBottomSheet(
                sheetState = rememberModalBottomSheetState(),
                banks =
                    listOf(
                        Bank(BankType.SHINHAN, R.drawable.ic_shinhan),
                        Bank(BankType.KB, R.drawable.ic_kb),
                    ),
                onClick = {},
            )
        }

        composeTestRule.onNodeWithText("신한카드").assertIsDisplayed()
        composeTestRule.onNodeWithText("국민카드").assertIsDisplayed()
    }
}
