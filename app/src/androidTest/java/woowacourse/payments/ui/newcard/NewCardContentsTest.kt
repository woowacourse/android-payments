package woowacourse.payments.ui.newcard

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@Suppress("ktlint:standard:function-naming")
class NewCardContentsTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            NewCardContents(LocalContext.current)
        }
    }

    @Test
    fun 카드_번호가_16자_미만이면_경고_메시지가_표시된다() {
        // given
        val target: SemanticsNodeInteraction = composeTestRule.onNodeWithText("카드 번호")

        // when
        target.performTextInput("0".repeat(15))

        // then
        composeTestRule.onNodeWithText("카드 번호는 숫자 16자입니다.").assertIsDisplayed()
    }

    @Test
    fun 카드_번호를_16자_넘게_입력할_경우_첫_16자만_표시된다() {
        // given
        val target: SemanticsNodeInteraction = composeTestRule.onNodeWithText("카드 번호")

        // when
        target.performTextInput("0".repeat(17))

        // then
        composeTestRule.onNodeWithText("0".repeat(16)).assertIsDisplayed()
        composeTestRule.onNodeWithText("0".repeat(17)).assertIsNotDisplayed()
    }

    @Test
    fun 카드_번호에_숫자가_아닌_문자가_있으면_경고_메시지가_표시된다() {
        // given
        val target: SemanticsNodeInteraction = composeTestRule.onNodeWithText("카드 번호")

        // when
        target.performTextInput("abcdabcdabcdabcd")

        // then
        composeTestRule.onNodeWithText("카드 번호는 숫자 16자입니다.").assertIsDisplayed()
    }
}
