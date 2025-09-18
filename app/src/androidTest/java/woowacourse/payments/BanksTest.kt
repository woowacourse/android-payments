package woowacourse.payments

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.domain.BankType
import woowacourse.payments.ui.newcard.banks.Banks
import woowacourse.payments.ui.newcard.banks.BanksTestTag

class BanksTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `은행_보드에_모든_은행타입이_표시된다`() {
        composeTestRule.setContent { Banks(onSelectCard = {}) }
        composeTestRule
            .onAllNodesWithTag(BanksTestTag.BANK_CONTAINER_TAG)
            .assertCountEquals(BankType.entries.size - EXCLUDE_NON_TYPE_COUNT)
    }

    companion object {
        private const val EXCLUDE_NON_TYPE_COUNT = -1
    }
}
