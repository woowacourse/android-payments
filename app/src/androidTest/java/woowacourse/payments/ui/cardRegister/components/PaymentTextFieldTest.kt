package woowacourse.payments.ui.cardRegister.components

import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTextReplacement
import androidx.compose.ui.text.input.PasswordVisualTransformation
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.common.CreditCardVisualTransformation
import woowacourse.payments.ui.common.DateVisualTransformation

class PaymentTextFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `매개변수onlyDigits를_true_값으로_전달하면_숫자만_입력이_된다`() {
        // given:
        var inputText by mutableStateOf("")

        // when:
        composeTestRule.setContent {
            PaymentTextField(
                label = "카드 번호",
                placeholder = "0000-0000-0000-0000",
                maxLength = 10,
                text = inputText,
                onlyDigits = true,
                onValueChanged = { inputText = it },
            )
        }

        composeTestRule.onNodeWithText("카드 번호").performTextInput("1234뭉abc5678")

        // then:
        assertThat(inputText).isEqualTo("12345678")
    }

    @Test
    fun 매개변수maxLength를_6자로_전달하면_6자까지만_입력이_된다() {
        // given:
        var inputText by mutableStateOf("")
        val maxLength = 6

        // when:
        composeTestRule.setContent {
            PaymentTextField(
                label = "닉네임",
                placeholder = "뭉치즈~",
                maxLength = maxLength,
                text = inputText,
                onValueChanged = { inputText = it },
            )
        }

        composeTestRule.onNodeWithText("닉네임").performTextInput("123456789")

        // then:
        assertThat(inputText).isEqualTo("123456")
        assertThat(inputText.length).isEqualTo(maxLength)
    }

    @Test
    fun 매개변수supportingText를_전달하면_해당_텍스트가_텍스트필드의_supportingText로_뜬다() {
        // given:
        val supportingTextValue = "3/30"

        // when:
        composeTestRule.setContent {
            PaymentTextField(
                label = "닉네임",
                placeholder = "뭉치즈~",
                maxLength = 10,
                text = "123456789",
                onValueChanged = {},
                supportingText = {
                    Text(text = supportingTextValue)
                },
            )
        }

        // then:
        composeTestRule.onNodeWithText(supportingTextValue).assertExists()
    }

    @Test
    fun 매개변수VisualTransformation을_PasswordVisualTransformation으로_설정하면_text가_숨겨져_보인다() {
        // given:
        val password = "123456789"

        // when:
        composeTestRule.setContent {
            PaymentTextField(
                label = "비밀번호",
                placeholder = "비밀번호를 입력하세요",
                maxLength = 10,
                text = password,
                onValueChanged = {},
                visualTransformation = PasswordVisualTransformation(),
            )
        }

        // then:
        val maskedText = "•".repeat(password.length)
        composeTestRule.onNodeWithText(maskedText).assertExists()
    }

    @Test
    fun 매개변수CreditCardVisualTransformation으로_설정하면_4자마다_하이픈이_추가되어_보인다() {
        // given:
        var inputText by mutableStateOf("")

        // when:
        composeTestRule.setContent {
            PaymentTextField(
                label = "카드번호",
                placeholder = "0000-0000-0000-0000",
                maxLength = 16,
                text = inputText,
                onValueChanged = { inputText = it },
                visualTransformation = CreditCardVisualTransformation(),
            )
        }
        composeTestRule
            .onNode(hasSetTextAction())
            .performTextReplacement("1234567890123456")

        // then:
        composeTestRule.onNodeWithText("1234 - 5678 - 9012 - 3456").assertExists()
    }

    @Test
    fun 매개변수DateVisualTransformation으로_설정하면_2자마다_슬래시가_추가되어_보인다() {
        // given:
        var inputText by mutableStateOf("0130")

        // when:
        composeTestRule.setContent {
            PaymentTextField(
                label = "만료일",
                placeholder = "MM/YY",
                maxLength = 4,
                text = inputText,
                onValueChanged = { inputText = it },
                visualTransformation = DateVisualTransformation(),
            )
        }

        composeTestRule
            .onNode(hasSetTextAction())
            .performTextReplacement("0130")

        // then:
        composeTestRule.onNodeWithText("01 / 30").assertExists()
    }
}
