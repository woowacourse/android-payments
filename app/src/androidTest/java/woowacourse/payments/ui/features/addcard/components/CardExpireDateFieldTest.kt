package woowacourse.payments.ui.features.addcard.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.text.AnnotatedString
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.features.addcard.CardUiState
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class CardExpireDateFieldTest {
    @get:Rule
    val compose = createComposeRule()
    private lateinit var cardExpireDateField: SemanticsNodeInteraction

    @Before
    fun setUp() {
        compose.setContent {
            var text by remember { mutableStateOf("") }

            AndroidpaymentsTheme(dynamicColor = false) {
                CardNumberField(
                    value = text,
                    onValueChange = {
                        text = CardUiState().withExpireDate(it).expireDate
                    },
                )
            }
        }

        cardExpireDateField = compose.onNode(hasSetTextAction(), useUnmergedTree = true)
    }

    @Test
    fun 카드_만료일_입력_필드에_숫자_입력시_값이_저장된다() {
        // given
        val input = "1234"

        // when
        cardExpireDateField.performTextInput(input)

        // then
        cardExpireDateField.assertExists()
        compose.onNode(hasSetTextAction(), useUnmergedTree = true).assert(
            SemanticsMatcher.expectValue(
                SemanticsProperties.InputText,
                AnnotatedString(input),
            ),
        )
    }

    @Test
    fun 카드_번호_입력_필드에_숫자는_4자까지_입력된다() {
        // given
        val input = "1".repeat(5)
        val expected = "1".repeat(4)

        // when
        cardExpireDateField.performTextInput(input)

        // then
        cardExpireDateField.assertExists()
        compose.onNode(hasSetTextAction(), useUnmergedTree = true).assert(
            SemanticsMatcher.expectValue(
                SemanticsProperties.InputText,
                AnnotatedString(expected),
            ),
        )
    }

    @Test
    fun 카드_만료일_입력_필드는_반각_숫자만_입력된다() {
        // given
        val input = "1테스트2🤔3test１２３４５4"
        val expected = "1234"

        // when
        cardExpireDateField.performTextInput(input)

        // then
        cardExpireDateField.assertExists()
        compose.onNode(hasSetTextAction(), useUnmergedTree = true).assert(
            SemanticsMatcher.expectValue(
                SemanticsProperties.InputText,
                AnnotatedString(expected),
            ),
        )
    }
}
