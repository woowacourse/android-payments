package woowacourse.payments.newcard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.newcard.component.CardNumberTextField

class CardNumberTextFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `카드_번호_입력란을_누르면_플레이스홀더가_보인다`() {
        // given
        composeTestRule.setContent {
            var value by remember { mutableStateOf("") }
            CardNumberTextField(
                value = value,
                onValueChange = { value = it },
            )
        }

        // when
        composeTestRule.onNodeWithText("카드 번호").performClick()

        // then
        composeTestRule.onNodeWithText("0000-0000-0000-0000").assertIsDisplayed()
    }

    @Test
    fun `카드_번호_입력_시_대시가_자동으로_추가된다`() {
        // given
        composeTestRule.setContent {
            var value by remember { mutableStateOf("") }
            CardNumberTextField(
                value = value,
                onValueChange = { value = it },
            )
        }

        // when
        composeTestRule.onNodeWithText("카드 번호").performTextInput("123456789")

        // then
        composeTestRule.onNodeWithText("카드 번호").assertTextContains("1234-5678-9")
    }

    @Test
    fun `카드_번호_입력란에_문자는_입력되지_않는다`() {
        // given
        composeTestRule.setContent {
            var value by remember { mutableStateOf("") }
            CardNumberTextField(
                value = value,
                onValueChange = { value = it },
            )
        }
        val textFieldNode = composeTestRule.onNode(hasSetTextAction(), useUnmergedTree = true)

        // when
        textFieldNode.performTextInput("크림")

        // then
        textFieldNode.assertTextEquals("")
    }
}
