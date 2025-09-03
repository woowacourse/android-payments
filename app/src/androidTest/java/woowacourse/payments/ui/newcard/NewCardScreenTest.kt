@file:Suppress("ktlint:standard:function-naming")

package woowacourse.payments.ui.newcard

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

class NewCardScreenTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun 각_항목에_맞는_입력_영역의_라벨들이_표시된다() {
        // given & when
        composeRule.setContent { NewCardScreen() }

        // then
        composeRule
            .onNodeWithText("카드 번호")
            .assertIsDisplayed()
        composeRule
            .onNodeWithText("만료일")
            .assertIsDisplayed()
        composeRule
            .onNodeWithText("카드 소유자 이름(선택)")
            .assertIsDisplayed()
        composeRule
            .onNodeWithText("비밀번호")
            .assertIsDisplayed()
    }

    @Test
    fun 카드_번호는_숫자만_입력되고_구분자가_포함되어_표시된다() {
        // given
        composeRule.setContent { NewCardScreen() }
        val cardNumberField = composeRule.onNode(hasText("카드 번호") and hasSetTextAction())

        // when
        cardNumberField.performTextInput("1234a56!78")

        // then
        composeRule
            .onNodeWithText("1234${SEPARATOR_GROUP}5678")
            .assertIsDisplayed()
    }

    @Test
    fun 카드번호는_최대_16자_입력된다() {
        // given
        composeRule.setContent { NewCardScreen() }
        val cardNumberField = composeRule.onNode(hasText("카드 번호") and hasSetTextAction())

        // when
        cardNumberField.performTextInput("12345678901234567890")

        // then
        composeRule
            .onNodeWithText(
                "1234${SEPARATOR_GROUP}5678${SEPARATOR_GROUP}9012${SEPARATOR_GROUP}3456",
            ).assertIsDisplayed()
    }

    @Test
    fun 만료일은_MM_YY_와_구분자가_포함되어_표시된다() {
        // given
        composeRule.setContent { NewCardScreen() }

        // when
        val expiryField = composeRule.onNode(hasText("만료일") and hasSetTextAction())
        expiryField.performTextInput("123")

        // then
        composeRule
            .onNodeWithText("12${SEPARATOR_EXPIRY}3")
            .assertIsDisplayed()

        // when
        expiryField.performTextClearance()
        expiryField.performTextInput("1225")

        // then
        composeRule
            .onNodeWithText("12${SEPARATOR_EXPIRY}25")
            .assertIsDisplayed()
    }

    @Test
    fun 입력한_이름의_글자_수가_표시된다() {
        // given
        composeRule.setContent { NewCardScreen() }

        // when
        val holderField = composeRule.onNode(hasText("카드 소유자 이름(선택)") and hasSetTextAction())
        holderField.performTextInput("ABCD")

        // then
        composeRule
            .onNodeWithText("4/30")
            .assertIsDisplayed()
    }

    @Test
    fun 비밀번호는_마스킹되어_보인다() {
        // given
        composeRule.setContent { NewCardScreen() }

        // when
        val pinField = composeRule.onNode(hasText("비밀번호") and hasSetTextAction())
        pinField.performTextInput("1234")

        // then
        composeRule
            .onNodeWithText("••••")
            .assertIsDisplayed()
    }

    companion object {
        private const val SEPARATOR_GROUP = " - "
        private const val SEPARATOR_EXPIRY = " / "
    }
}
