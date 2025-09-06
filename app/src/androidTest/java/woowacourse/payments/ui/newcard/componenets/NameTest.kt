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
import woowacourse.payments.ui.newcard.components.NameTextField

class NameTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            var text: String by remember { mutableStateOf("") }

            NameTextField(
                modifier = Modifier,
                value = text,
                onValueChange = { text = it },
                maxLength = 30,
            )
        }
    }

    @Test
    fun `카드_소유자가_라벨로_보인다`() {
        // then
        composeTestRule
            .onNode(hasText("카드 소유자 이름 (선택)") and hasSetTextAction())
            .assertExists()
    }

    @Test
    fun `입력칸을_클릭하면_기본값이_보인다`() {
        // given
        val textField = composeTestRule.onNode(hasText("카드 소유자 이름 (선택)"))

        // when
        textField
            .performClick()

        // then
        composeTestRule
            .onNodeWithText("카드에 표시된 이름을 입력하세요.")
            .assertExists()
    }

    @Test
    fun `이름을_입력하면_글자수가_늘어난다`() {
        // given
        val textField = composeTestRule.onNode(hasText("카드 소유자 이름 (선택)"))

        // when
        textField.performTextInput("hwannow")

        // then
        composeTestRule
            .onNodeWithText("7 / 30")
            .assertIsDisplayed()
    }

    @Test
    fun `이름은_최대_길이까지만_입력_가능하다`() {
        // given
        val textField = composeTestRule.onNode(hasText("카드 소유자 이름 (선택)"))

        // when
        repeat(50) {
            textField.performTextInput("A")
        }

        // then
        composeTestRule
            .onNodeWithText("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")
            .assertIsDisplayed()
    }
}
