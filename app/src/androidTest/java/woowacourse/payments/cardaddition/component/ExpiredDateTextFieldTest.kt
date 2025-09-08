package woowacourse.payments.cardaddition.component

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ExpiredDateTextFieldTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Before
    fun setUp() {
        composeRule.setContent {
            val (expiredDate: String, setExpiredDate: (String) -> Unit) =
                remember {
                    mutableStateOf(
                        "",
                    )
                }

            ExpiredDateTextField(
                value = expiredDate,
                onValueChange = setExpiredDate,
            )
        }
    }

    @Test
    fun `만료일의_경우_입력할_때_자동으로_기호가_삽입된다`() {
        // when
        composeRule
            .onNodeWithText("")
            .performTextInput("1225")

        // then
        composeRule
            .onNodeWithText("12 / 25")
            .assertIsDisplayed()
    }

    @Test
    fun `4자리가_아닐_경우_에러_메시지를_출력한다`() {
        // when
        composeRule
            .onNodeWithText("")
            .performTextInput("12")

        // then
        composeRule
            .onNodeWithText("올바른 형식이 아닙니다.")
            .assertIsDisplayed()
    }

    @Test
    fun `만료_달이_올바르지_않을_경우_에러_메시지를_출력한다`() {
        // when
        composeRule
            .onNodeWithText("")
            .performTextInput("1325")

        // then
        composeRule
            .onNodeWithText("올바른 형식이 아닙니다.")
            .assertIsDisplayed()
    }
}
