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
import woowacourse.payments.newcard.component.ExpiredDateTextField

class ExpiredDateTextFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `만료일_입력란을_누르면_플레이스홀더가_보인다`() {
        // given
        composeTestRule.setContent {
            var value by remember { mutableStateOf("") }
            ExpiredDateTextField(
                value = value,
                onValueChange = { value = it },
            )
        }

        // when
        composeTestRule.onNodeWithText("만료일").performClick()

        // then
        composeTestRule.onNodeWithText("MM/YY").assertIsDisplayed()
    }

    @Test
    fun `만료일_입력_시_슬래시가_자동으로_추가된다`() {
        // given
        composeTestRule.setContent {
            var value by remember { mutableStateOf("") }
            ExpiredDateTextField(
                value = value,
                onValueChange = { value = it },
            )
        }

        // when
        composeTestRule.onNodeWithText("만료일").performTextInput("0925")

        // then
        composeTestRule.onNodeWithText("만료일").assertTextContains("09/25")
    }

    @Test
    fun `만료일_입력란에는_문자는_입력되지_않는다`() {
        // given
        composeTestRule.setContent {
            var value by remember { mutableStateOf("") }
            ExpiredDateTextField(
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

    @Test
    fun `만료일이_유효하지_않으면_경고_문구가_표시된다`() {
        // given
        composeTestRule.setContent {
            var value by remember { mutableStateOf("") }
            ExpiredDateTextField(
                value = value,
                onValueChange = { value = it },
            )
        }

        // when
        composeTestRule.onNodeWithText("만료일").performTextInput("9900")

        // then
        composeTestRule.onNodeWithText("만료일").assertTextContains("유효하지 않은 만료일자 형식입니다.")
    }
}
