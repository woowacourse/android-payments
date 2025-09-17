package woowacourse.payments.component

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

    @Test
    fun bottomSheet에_BankItem목록이_표시된다() {
        // given
        composeTestRule.setContent {
            BankSelectBottomSheet(
                onBankSelected = {},
                onDismiss = {},
            )
        }

        BankType.entries.filterNot { it == BankType.NOT_SELECTED }.forEach { bank ->
            composeTestRule
                .onNodeWithText(bank.toPresentation().name)
                .assertIsDisplayed()
        }
    }
}
