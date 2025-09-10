package woowacourse.payments.ui.newcard

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

@Suppress("ktlint:standard:function-naming")
class NewCardScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 모든_입력_필드의_라벨이_표시된다() {
        // given
        composeTestRule.setContent { NewCardScreen() }

        // then
        composeTestRule.onNodeWithText("카드 번호").assertIsDisplayed()
        composeTestRule.onNodeWithText("만료일").assertIsDisplayed()
        composeTestRule.onNodeWithText("카드 소유자 이름 (선택)").assertIsDisplayed()
        composeTestRule.onNodeWithText("카드 비밀번호").assertIsDisplayed()
    }
}
