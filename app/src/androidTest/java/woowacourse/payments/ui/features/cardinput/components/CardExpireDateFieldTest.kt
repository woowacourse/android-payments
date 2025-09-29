package woowacourse.payments.ui.features.cardinput.components

import androidx.activity.ComponentActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.text.AnnotatedString
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.R
import woowacourse.payments.ui.mapper.CardMapper.getExpireDateUiState
import woowacourse.payments.ui.theme.AndroidpaymentsTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CardExpireDateFieldTest {
    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()
    private lateinit var cardExpireDateField: SemanticsNodeInteraction

    @Before
    fun setUp() {
        compose.setContent {
            var text by remember { mutableStateOf("") }

            AndroidpaymentsTheme(dynamicColor = false) {
                CardExpireDateField(
                    value = text,
                    onValueChange = { text = it },
                    expireDateUiState = getExpireDateUiState(text),
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

    @Test
    fun 카드_만료일_입력_필드에_잘못된_월_입력시_에러가_표시된다() {
        // given
        val input = "1399"
        val errorLabel =
            compose.activity.getString(R.string.add_card_expire_date_month_error_message)

        // when
        cardExpireDateField.performTextInput(input)

        // then
        compose.onNodeWithText(errorLabel).assertExists()
    }

    @Test
    fun 카드_만료일_입력_필드에_과거의_연월_입력시_만료카드_에러가_표시된다() {
        // given
        val input = LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("MMyy"))
        val errorLabel =
            compose.activity.getString(R.string.add_card_expire_date_past_error_message)

        // when
        cardExpireDateField.performTextInput(input)

        // then
        compose.onNodeWithText(errorLabel).assertExists()
    }
}
