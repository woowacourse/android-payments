package woowacourse.payments.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.domain.BankType
import woowacourse.payments.ui.component.BankItem

class BankItemTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 카드사_로고_이미지와_이름이_표시된다() {
        val bankType = BankType.BC

        composeTestRule.setContent {
            BankItem(
                bankType = bankType,
                onClick = {},
            )
        }

        composeTestRule
            .onNodeWithContentDescription("은행 아이템 이미지")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("BC카드")
            .assertIsDisplayed()
    }
}
