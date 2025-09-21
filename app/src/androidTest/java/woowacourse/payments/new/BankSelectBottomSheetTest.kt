package woowacourse.payments.new

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.R
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.ui.view.new.BankSelectRow

class BankSelectBottomSheetTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun `모든_은행_아이콘과_이름이_보인다`() {
        composeTestRule.setContent {
            BankSelectRow(onClick = {})
        }

        CardCompany.entries.forEach { bankType ->
            val bankName =
                when (bankType) {
                    CardCompany.BC -> composeTestRule.activity.getString(R.string.bank_bc)
                    CardCompany.SHINHAN -> composeTestRule.activity.getString(R.string.bank_sinhan)
                    CardCompany.KAKAO -> composeTestRule.activity.getString(R.string.bank_kakao)
                    CardCompany.HYUNDAE -> composeTestRule.activity.getString(R.string.bank_hyundae)
                    CardCompany.WOORI -> composeTestRule.activity.getString(R.string.bank_woori)
                    CardCompany.LOTTE -> composeTestRule.activity.getString(R.string.bank_lotte)
                    CardCompany.HANA -> composeTestRule.activity.getString(R.string.bank_hana)
                    CardCompany.KB -> composeTestRule.activity.getString(R.string.bank_kb)
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
