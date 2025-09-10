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
import woowacourse.payments.ui.addcard.textfields.CardHolderNameTextField
import woowacourse.payments.ui.model.CardUiModel

@Suppress("ktlint:standard:function-naming")
class CardHolderNameTextFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent { CardHolderNameTextField(mutableStateOf(CardUiModel.EMPTY)) }
    }

    @Test
    fun 카드_소유자_이름을_30자_넘게_입력할_경우_첫_30자만_표시된다() {
        // given
        val target: SemanticsNodeInteraction = composeTestRule.onNodeWithText("카드 소유자 이름(선택)")

        // when
        target.performTextInput("0".repeat(31))

        // then
        composeTestRule.onNodeWithText("0".repeat(30)).assertIsDisplayed()
    }

    @Test
    fun 카드_소유자_이름에_입력된_문자의_개수를_표시한다() {
        // given
        val target: SemanticsNodeInteraction = composeTestRule.onNodeWithText("카드 소유자 이름(선택)")

        // when
        target.performTextInput("Hello World")

        // then
        composeTestRule.onNodeWithText("11 / 30").assertIsDisplayed()
    }
}
