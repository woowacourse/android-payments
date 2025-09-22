package woowacourse.payments.ui.screen.cardAddition.component

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.model.IssuingBank

class BankSelectRowTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Before
    fun setUp() {
        composeRule.setContent {
            BankSelectRow(
                issuingBanks = listOf(IssuingBank.KAKAO, IssuingBank.HYUNDAI),
                onBankSelect = {},
                column = 2,
            )
        }
    }

    @Test
    fun `카드사_목록이_출력된다`() {
        // then
        composeRule
            .onAllNodesWithContentDescription("카드사 정보")
            .assertCountEquals(2)

        composeRule
            .onNode(hasContentDescription("카드사 이름") and hasText("카카오뱅크"))
            .assertIsDisplayed()

        composeRule
            .onNode(hasContentDescription("카드사 이름") and hasText("현대카드"))
            .assertIsDisplayed()
    }
}
