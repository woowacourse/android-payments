package woowacourse.payments.ui.addcard

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.domain.BankType

class BankSelectBottomSheetTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private fun setupBankSelectBottomSheet(
        onDismiss: () -> Unit = {},
        onBankSelect: (BankType) -> Unit = {},
    ) {
        composeTestRule.setContent {
            BankSelectBottomSheet(
                onDismiss = onDismiss,
                onBankSelect = onBankSelect,
            )
        }
    }

    @Test
    fun `은행_선택_시_onBankSelect가_호출된다`() {
        // given
        var selectedBank: BankType? = null
        setupBankSelectBottomSheet(onBankSelect = { bankType -> selectedBank = bankType })

        // when
        composeTestRule.onNodeWithText("국민카드").performClick()

        // then
        assert(selectedBank == BankType.KB)
    }
}
