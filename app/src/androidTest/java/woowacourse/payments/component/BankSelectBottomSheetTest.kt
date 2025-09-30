package woowacourse.payments.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.domain.BankType
import woowacourse.payments.ui.component.BankSelectBottomSheet
import woowacourse.payments.ui.model.toPresentation

class BankSelectBottomSheetTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun bottomSheet에_BankItem목록이_표시된다() {
        // given
        composeTestRule.setContent {
            BankSelectBottomSheet(
                sheetState = rememberModalBottomSheetState(),
                banks = BankType.entries,
                onBankSelected = {},
                onDismiss = {},
            )
        }

        BankType.entries.forEach { bank ->
            composeTestRule
                .onNodeWithText(bank.toPresentation().name)
                .assertIsDisplayed()
        }
    }
}
