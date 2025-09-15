package woowacourse.payments.ui.screen.cardAddition.component

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.model.IssuingBank

class BankInfoTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun `카드사_아이콘과_이름이_출력된다`() {
        // given
        composeRule.setContent {
            BankInfo(
                onBankSelect = {},
                issuingBank = IssuingBank.KAKAO,
            )
        }

        // when
        // then
        composeRule
            .onNodeWithContentDescription("카드사 아이콘")
            .assertIsDisplayed()

        composeRule
            .onNodeWithContentDescription("카드사 이름")
            .assert(hasText("카카오뱅크"))
            .assertIsDisplayed()
    }

    @Test
    fun `카드사가_선택되지_않은_경우_이름은_빈_문자열이다`() {
        // given
        composeRule.setContent {
            BankInfo(onBankSelect = {})
        }

        // when
        // then
        composeRule
            .onNodeWithContentDescription("카드사 이름")
            .assert(hasText(""))
            .assertIsDisplayed()
    }
}
