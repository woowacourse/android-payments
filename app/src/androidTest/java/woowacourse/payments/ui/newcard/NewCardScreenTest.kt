@file:Suppress("ktlint:standard:function-naming")

package woowacourse.payments.ui.newcard

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class NewCardScreenTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun 프리뷰_카드를_클릭하면_은행선택_바텀시트가_열린다() {
        // given & when
        composeRule.setContent {
            NewCardScreen(
                onSaved = {},
                onFinish = {},
            )
        }

        // when
        composeRule.onNodeWithText("카드사를 선택해주세요").performClick()

        // then
        composeRule.onNodeWithText("우리카드").assertIsDisplayed()
    }
}
