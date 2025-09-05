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
import woowacourse.payments.ui.newcard.components.ExpirationDate

class ExpirationDateTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            var text: String by remember { mutableStateOf("") }

            ExpirationDate(
                modifier = Modifier,
                value = text,
                onValueChange = { text = it },
                label = "만료일",
                placeholder = "MM / YY",
                maxLength = 4,
            )
        }
    }

    @Test
    fun `만료일이_라벨로_보인다`() {
        // then
        composeTestRule
            .onNode(hasText("만료일") and hasSetTextAction())
            .assertExists()
    }

    @Test
    fun `입력칸을_클릭하면_기본값이_보인다`() {
        // given
        val textField = composeTestRule.onNode(hasText("만료일"))

        // when
        textField
            .performClick()

        // then
        composeTestRule
            .onNodeWithText("MM / YY")
            .assertExists()
    }

    @Test
    fun `만료일을_입력하면_구분자가_자동으로_삽입된다`() {
        // given
        val textField = composeTestRule.onNode(hasText("만료일"))

        // when
        textField.performTextInput("1125")

        // then
        composeTestRule
            .onNodeWithText("11 / 25")
            .assertIsDisplayed()
    }
}
