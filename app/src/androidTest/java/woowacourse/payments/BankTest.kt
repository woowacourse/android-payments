package woowacourse.payments

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.domain.BankType
import woowacourse.payments.ui.newcard.banks.Bank
import woowacourse.payments.ui.newcard.banks.BanksTestTag

class BankTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun `은행_컨테이너를_클릭하면_해당_은행타입으로_onSelectCard가_호출된다`() {
        var bankType: BankType? = null
        rule.setContent {
            Bank(BankType.BC, { bankType = it })
        }
        rule
            .onNodeWithTag(BanksTestTag.BANK_CONTAINER_TAG)
            .assertIsEnabled()
            .performClick()

        assert(bankType == BankType.BC)
    }

    @Test
    fun `은행_이름이_UI에_표시된다`() {
        rule.setContent {
            Bank(BankType.BC, {})
        }
        rule
            .onNodeWithTag(BanksTestTag.BANK_NAME_TAG, useUnmergedTree = true)
            .assert(hasText("BC카드"))
    }
}
