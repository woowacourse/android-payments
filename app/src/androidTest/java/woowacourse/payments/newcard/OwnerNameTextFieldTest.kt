package woowacourse.payments.newcard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.newcard.component.OwnerNameTextField

class OwnerNameTextFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `소유자명_입력란을_누르면_플레이스홀더가_보인다`() {
        // given
        composeTestRule.setContent {
            var value by remember { mutableStateOf("") }
            OwnerNameTextField(
                value = value,
                onValueChange = { value = it },
            )
        }

        // when
        composeTestRule.onNodeWithText("카드 소유자 이름(선택)").performClick()

        // then
        composeTestRule.onNodeWithText("카드에 표시된 이름을 입력하세요.").assertIsDisplayed()
    }

    @Test
    fun `소유자명_입력_시_글자수_표시가_증가한다`() {
        // given
        composeTestRule.setContent {
            var value by remember { mutableStateOf("") }
            OwnerNameTextField(
                value = value,
                onValueChange = { value = it },
            )
        }
        composeTestRule.onNodeWithText("카드 소유자 이름(선택)").assertTextContains("0/30")

        // when
        composeTestRule.onNodeWithText("카드 소유자 이름(선택)").performTextInput("크림")

        // then
        composeTestRule.onNodeWithText("카드 소유자 이름(선택)").assertTextContains("2/30")
    }
}
