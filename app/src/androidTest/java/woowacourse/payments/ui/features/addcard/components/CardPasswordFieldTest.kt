package woowacourse.payments.ui.features.addcard.components

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardPasswordFieldTest {
    @get:Rule
    val compose = createComposeRule()
    private lateinit var cardPasswordField: SemanticsNodeInteraction
    private lateinit var textState: MutableState<String>

    @Before
    fun setUp() {
        compose.setContent {
            val state = remember { mutableStateOf("") }
            textState = state

            AndroidpaymentsTheme(dynamicColor = false) {
                CardPasswordField(
                    value = state.value,
                    onValueChange = { state.value = it },
                )
            }
        }

        cardPasswordField = compose.onNode(hasSetTextAction(), useUnmergedTree = true)
    }

    @Test
    fun 카드_비밀번호_입력_필드에_숫자_입력시_값이_저장된다() {
        // given
        val input = "1234"

        // when
        cardPasswordField.performTextInput(input)

        // then
        compose.runOnIdle { assert(textState.value == "1234") }
    }

    @Test
    fun 카드_비밀번호_입력_필드에_숫자는_4자까지_입력된다() {
        // given
        val input = "1".repeat(5)
        val expected = "1".repeat(4)

        // when
        cardPasswordField.performTextInput(input)

        // then
        compose.runOnIdle { assert(textState.value == "1111") }
    }

    @Test
    fun 카드_비밀번호_입력_필드는_반각_숫자만_입력된다() {
        // given
        val input = "1테스트2🤔3test１２３４５4"
        val expected = "1234"

        // when
        cardPasswordField.performTextInput(input)

        // then
        compose.runOnIdle { assert(textState.value == "1234") }
    }

    @Test
    fun 카드_비밀번호_입력_필드는_입력값이_특수문자로_가려진다() {
        // given
        val input = "1234"
        val expected = "•".repeat(4)

        // when
        cardPasswordField.performTextInput(input)

        // then
        compose.onNodeWithText(expected).assertIsDisplayed()
    }
}
