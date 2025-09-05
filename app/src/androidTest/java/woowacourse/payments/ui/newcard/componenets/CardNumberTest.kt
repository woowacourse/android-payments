package woowacourse.payments.ui.newcard.componenets

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.newcard.components.CardNumberTextField

class CardNumberTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            var text: String by remember { mutableStateOf("") }

            CardNumberTextField(
                modifier = Modifier,
                value = text,
                onValueChange = { text = it },
                label = "카드 번호",
                placeholder = "0000 - 0000 - 0000 - 0000",
                maxLength = 16,
            )
        }
    }

    @Test
    fun `카드_번호가_라벨로_보인다`() {
        // then
        composeTestRule
            .onNode(hasText("카드 번호") and hasSetTextAction())
            .assertExists()
    }

    @Test
    fun `입력칸을_클릭하면_기본값이_보인다`() {
        // given
        val textField = composeTestRule.onNode(hasText("카드 번호"))

        // when
        textField
            .performClick()

        // then
        composeTestRule
            .onNodeWithText("0000 - 0000 - 0000 - 0000")
            .assertExists()
    }

    @Test
    fun `카드_번호를_입력하면_구분자가_자동으로_삽입된다`() {
        // given
        val textField = composeTestRule.onNode(hasText("카드 번호"))

        // when
        textField.performTextInput("123456789")

        // then
        composeTestRule
            .onNodeWithText("1234 - 5678 - 9")
            .assertIsDisplayed()
    }
}
