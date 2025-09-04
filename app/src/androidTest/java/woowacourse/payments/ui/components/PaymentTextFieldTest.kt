package woowacourse.payments.ui.components

import androidx.compose.material3.Text
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.text.input.PasswordVisualTransformation
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
        val text = "1234뭉"

        // when:
        composeTestRule.setContent {
            PaymentTextField(
                label = "카드 번호",
                hint = "0000-0000-0000-0000",
                maxLength = 4,
                text = text,
                onlyDigits = true,
                onValueChanged = { "" }
            )
        }

        // then:
        composeTestRule.onNodeWithText("카드 번호").performTextInput("1234")
    }

    @Test
    fun 매개변수maxLength를_6자로_전달하면_6자까지만_입력이_된다() {
        // given:
        val maxLength = 6

        // when:
        composeTestRule.setContent {
            PaymentTextField(
                label = "닉네임",
                hint = "뭉치즈~",
                maxLength = maxLength,
                text = "123456789",
                onValueChanged = { "" }
            )
        }

        // then:
        composeTestRule.onNodeWithText("닉네임").performTextInput("123456")
    }

    @Test
    fun 매개변수supportingText를_전달하면_해당_텍스트가_텍스트필드의_supportingText로_뜬다() {
        // given:
        val supportingTextValue = "3/30"

        // when:
        composeTestRule.setContent {
            PaymentTextField(
                label = "닉네임",
                hint = "뭉치즈~",
                maxLength = 10,
                text = "123456789",
                onValueChanged = {},
                supportingText = {
                    Text(text = supportingTextValue)
                }
            )
        }

        // then:
        composeTestRule.onNodeWithText(supportingTextValue).assertExists()
    }

    @Test
    fun 매개변수VisualTransformation을_PasswordVisualTransformation으로_설정하면_text가_숨겨져_보인다() {
        // given:
        // when:
        composeTestRule.setContent {
            PaymentTextField(
                label = "닉네임",
                hint = "뭉치즈~",
                maxLength = 10,
                text = "123456789",
                onValueChanged = {},
                visualTransformation = PasswordVisualTransformation()
            )
        }

        // then:
        composeTestRule.onNodeWithText("닉네임").performTextInput("•••••••••")
    }

    @Test
    fun 매개변수CreditCardVisualTransformation으로_설정하면_4자마다_하이픈이_추가되어_보인다() {
        // given:
        // when:
        composeTestRule.setContent {
            PaymentTextField(
                label = "카드번호",
                hint = "뭉치즈~",
                maxLength = 10,
                text = "123456789",
                onValueChanged = {},
                visualTransformation = CreditCardVisualTransformation()
            )
        }

        // then:
        composeTestRule.onNodeWithText("카드번호").performTextInput("1234-5678-9")
    }

    @Test
    fun 매개변수DateVisualTransformation으로_설정하면_2자마다_슬래시가_추가되어_보인다() {
        // given:
        // when:
        composeTestRule.setContent {
            PaymentTextField(
                label = "만료일",
                hint = "MM/YY",
                maxLength = 4,
                text = "0130",
                onValueChanged = {},
                visualTransformation = DateVisualTransformation()
            )
        }

        // then:
        composeTestRule.onNodeWithText("만료일").performTextInput("01/30")
    }
}