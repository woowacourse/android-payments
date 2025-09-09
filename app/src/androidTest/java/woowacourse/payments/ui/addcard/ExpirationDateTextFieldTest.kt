package woowacourse.payments.ui.addcard

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.addcard.textfields.ExpirationDateTextField

@Suppress("ktlint:standard:function-naming")
class ExpirationDateTextFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            ExpirationDateTextField(
                mutableStateOf(""),
                mutableStateOf(false),
            )
        }
    }

    @Test
    fun 만료일을_4자_넘게_입력할_경우_첫_4자만_표시된다() {
        // given
        val target: SemanticsNodeInteraction = composeTestRule.onNodeWithText("만료일")

        // when
        target.performTextInput("01250")

        // then
        composeTestRule.onNodeWithText("01 / 25").assertIsDisplayed()
    }

    @Test
    fun 만료일이_MM_YY_형식이_아니면_경고_메시지가_표시된다_1() {
        // given
        val target: SemanticsNodeInteraction = composeTestRule.onNodeWithText("만료일")

        // when
        target.performTextInput("abcd")

        // then
        composeTestRule.onNodeWithText("유효하지 않은 만료일입니다.").assertIsDisplayed()
    }

    @Test
    fun 만료일이_MM_YY_형식이_아니면_경고_메시지가_표시된다_2() {
        // given
        val target: SemanticsNodeInteraction = composeTestRule.onNodeWithText("만료일")

        // when
        target.performTextInput("1325")

        // then
        composeTestRule.onNodeWithText("유효하지 않은 만료일입니다.").assertIsDisplayed()
    }

    @Test
    fun 만료일을_입력_시_2자_단위로_기호를_삽입한다() {
        // given
        val target: SemanticsNodeInteraction = composeTestRule.onNodeWithText("만료일")

        // when
        target.performTextInput("0925")

        // then
        composeTestRule.onNodeWithText("09 / 25").assertIsDisplayed()
    }
}
