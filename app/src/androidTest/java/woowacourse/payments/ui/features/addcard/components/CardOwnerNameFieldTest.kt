package woowacourse.payments.ui.features.addcard.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.text.AnnotatedString
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.features.addcard.CardUiState
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardOwnerNameFieldTest {
    @get:Rule
    val compose = createComposeRule()
    private lateinit var cardOwnerNameField: SemanticsNodeInteraction

    @Before
    fun setUp() {
        compose.setContent {
            var text by remember { mutableStateOf("") }

            AndroidpaymentsTheme(dynamicColor = false) {
                CardOwnerNameField(
                    value = text,
                    onValueChange = { text = CardUiState().withOwnerName(it).ownerName },
                )
            }
        }

        cardOwnerNameField = compose.onNode(hasSetTextAction(), useUnmergedTree = true)
    }

    @Test
    fun 카드_소유자_이름_입력_필드에_값이_저장된다() {
        // given
        val input = "메다"

        // when
        cardOwnerNameField.performTextInput(input)

        // then
        cardOwnerNameField.assertExists()
        compose.onNode(hasSetTextAction(), useUnmergedTree = true).assert(
            SemanticsMatcher.expectValue(
                SemanticsProperties.InputText,
                AnnotatedString(input),
            ),
        )
    }

    @Test
    fun 카드_소유자_이름을_입력하면_글자_수가_업데이트_된다() {
        // given
        val input = "메다"

        // when
        cardOwnerNameField.performTextInput(input)

        // then
        compose.onNodeWithText("2/30").assertIsDisplayed()
    }

    @Test
    fun 카드_소유자_이름은_30자까지_입력된다() {
        // given
        val input = "메".repeat(31)
        val expected = "메".repeat(30)

        // when
        cardOwnerNameField.performTextInput(input)

        // then
        compose.onNodeWithText(expected).assertIsDisplayed()
    }
}
