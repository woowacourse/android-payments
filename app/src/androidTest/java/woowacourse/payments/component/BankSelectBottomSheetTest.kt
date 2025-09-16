package woowacourse.payments.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.domain.BankType
import woowacourse.payments.ui.component.BankSelectBottomSheet
import woowacourse.payments.ui.util.toBankUiModel

class BankSelectBottomSheetTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun bottomSheet에_BankItem목록이_표시된다() {
        // given
        composeTestRule.setContent {
            BankSelectBottomSheet(
                onBankSelected = {},
                onDismiss = {},
            )
        }

        BankType.entries.filter { it != BankType.NOT_SELECTED }.forEach { bank ->
            composeTestRule
                .onNodeWithText(bank.toBankUiModel().name)
                .assertIsDisplayed()
        }
    }
}
