package woowacourse.payments

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.domain.BankType
import woowacourse.payments.ui.model.toLocalBankUiModel
import woowacourse.payments.ui.newcard.banks.Bank
import woowacourse.payments.ui.newcard.banks.BanksGrid
import woowacourse.payments.ui.newcard.banks.BanksTestTag

class BanksTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `은행_보드에_모든_은행타입이_표시된다`() {
        composeTestRule.setContent {
            BanksGrid(BankType.entries.map { it.toLocalBankUiModel() }, 4) {
                Bank(it, {})
            }
        }
        composeTestRule
            .onAllNodesWithTag(BanksTestTag.BANK_CONTAINER_TAG)
            .assertCountEquals(BankType.entries.size)
    }
}
