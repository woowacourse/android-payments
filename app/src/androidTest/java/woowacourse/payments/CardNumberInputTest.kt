package woowacourse.payments

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.component.CardNumberInput

class CardNumberInputTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            CardNumberInput(
                cardNumber = null,
                onCardNumberChange = { },
            )
        }
    }

    @Test
    fun 초기_화면에_카드_번호_텍스트가_표시된다() {
        // then
        composeTestRule
            .onNodeWithText("카드 번호")
            .assertIsDisplayed()
    }

    @Test
    fun 입력창을_클릭하면_라벨과_함께_placeholder가_표시된다() {
        // given

        // when
        composeTestRule
            .onNodeWithText("카드 번호")
            .performClick()

        // then
        composeTestRule
            .onNodeWithText("카드 번호")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("0000 - 0000 - 0000 - 0000")
            .assertIsDisplayed()
    }

    @Test
    fun 카드_번호를_입력하면_4글자_기준으로_기호가_삽입된다() {
        // given
        val input = "1234567887654321"

        // when
        composeTestRule
            .onNode(hasSetTextAction())
            .performTextInput(input)

        // then
        composeTestRule
            .onNodeWithText("1234-5678-8765-4321")
            .assertIsDisplayed()
    }
}
