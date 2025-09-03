package woowacourse.payments

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.component.ExpiredInput

class ExpiredInputTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            ExpiredInput(
                expired = null,
                onExpiredChange = { },
            )
        }
    }

    @Test
    fun 초기_화면에_만료일_텍스트가_표시된다() {
        // then
        composeTestRule
            .onNodeWithText("만료일")
            .assertIsDisplayed()
    }

    @Test
    fun 만료일을_입력하면_2글자_기준으로_기호가_삽입된다() {
        // given
        val input = "1029"

        // when
        composeTestRule
            .onNode(hasSetTextAction())
            .performTextInput(input)

        // then
        composeTestRule
            .onNodeWithText("10/29")
            .assertIsDisplayed()
    }
}
