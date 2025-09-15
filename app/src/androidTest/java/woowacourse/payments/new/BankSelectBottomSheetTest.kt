package woowacourse.payments.new

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.R
import woowacourse.payments.domain.Banks
import woowacourse.payments.ui.core.CompanyResourceProvider
import woowacourse.payments.ui.view.new.BankSelectRow

class BankSelectBottomSheetTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun `모든_은행_아이콘과_이름이_보인다`() {
        composeTestRule.setContent {
            BankSelectRow(resourceProvider = CompanyResourceProvider(), onClick = {})
        }

        Banks.entries.forEach { bankType ->
            val bankName =
                when (bankType) {
                    Banks.BC -> composeTestRule.activity.getString(R.string.bank_bc)
                    Banks.SHINHAN -> composeTestRule.activity.getString(R.string.bank_sinhan)
                    Banks.KAKAO -> composeTestRule.activity.getString(R.string.bank_kakao)
                    Banks.HYUNDAE -> composeTestRule.activity.getString(R.string.bank_hyundae)
                    Banks.WOORI -> composeTestRule.activity.getString(R.string.bank_woori)
                    Banks.LOTTE -> composeTestRule.activity.getString(R.string.bank_lotte)
                    Banks.HANA -> composeTestRule.activity.getString(R.string.bank_hana)
                    Banks.KB -> composeTestRule.activity.getString(R.string.bank_kb)
                }

            composeTestRule
                .onNodeWithContentDescription(
                    bankName,
                ).assertIsDisplayed()

            composeTestRule
                .onNodeWithText(
                    bankName,
                ).assertIsDisplayed()
        }
    }
}
