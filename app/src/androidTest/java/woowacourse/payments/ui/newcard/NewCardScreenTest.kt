package woowacourse.payments.ui.newcard

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

@Suppress("ktlint:standard:function-naming")
class NewCardScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 모든_입력_필드의_라벨이_표시된다() {
        // given
        composeTestRule.setContent { NewCardScreen() }

        // then
        composeTestRule.onNodeWithText("카드 번호").assertIsDisplayed()
        composeTestRule.onNodeWithText("만료일").assertIsDisplayed()
        composeTestRule.onNodeWithText("카드 소유자 이름 (선택)").assertIsDisplayed()
        composeTestRule.onNodeWithText("카드 비밀번호").assertIsDisplayed()
    }

    @Test
    fun 카드_번호_필드에_값을_입력하면_입력값이_표시된다() {
        // given
        composeTestRule.setContent { NewCardScreen() }

        // when
        val input = "1234567890123456" // 16자리

        composeTestRule
            .onNodeWithText("카드 번호")
            .performTextInput(input)

        // then
        composeTestRule.onNodeWithText(input).assertIsDisplayed()
    }

    @Test
    fun 카드_번호_필드에_최대_길이를_초과하여_값을_입력하면_제한된_길이까지_표시된다() {
        // given
        composeTestRule.setContent { NewCardScreen() }

        // when
        val input = "12345678901234567" // 17자리
        val expected = input.take(16)

        composeTestRule
            .onNodeWithText("카드 번호")
            .performTextInput(input)

        // then
        composeTestRule.onNodeWithText(expected).assertIsDisplayed()
    }

    @Test
    fun 카드_만료일_필드에_값을_입력하면_입력값이_표시된다() {
        // given
        composeTestRule.setContent { NewCardScreen() }

        // when
        val input = "1234" // 4자리

        composeTestRule
            .onNodeWithText("만료일")
            .performTextInput(input)

        // then
        composeTestRule.onNodeWithText(input).assertIsDisplayed()
    }

    @Test
    fun 카드_만료일_필드에_최대_길이를_초과하여_값을_입력하면_제한된_길이까지_표시된다() {
        // given
        composeTestRule.setContent { NewCardScreen() }

        // when
        val input = "12345" // 5자리
        val expected = input.take(4)

        composeTestRule
            .onNodeWithText("만료일")
            .performTextInput(input)

        // then
        composeTestRule.onNodeWithText(expected).assertIsDisplayed()
    }

    @Test
    fun 카드_소유자_이름_필드에_값을_입력하면_입력값이_표시된다() {
        // given
        composeTestRule.setContent { NewCardScreen() }

        // when
        val input = "123456789012345678901234567890" // 30자리

        composeTestRule
            .onNodeWithText("카드 소유자 이름 (선택)")
            .performTextInput(input)

        // then
        composeTestRule.onNodeWithText(input).assertIsDisplayed()
    }

    @Test
    fun 카드_소유자_이름_필드에_최대_길이를_초과하여_값을_입력하면_제한된_길이까지_표시된다() {
        // given
        composeTestRule.setContent { NewCardScreen() }

        // when
        val input = "1234567890123456789012345678901" // 31자리
        val expected = input.take(30)

        composeTestRule
            .onNodeWithText("카드 소유자 이름 (선택)")
            .performTextInput(input)

        // then
        composeTestRule.onNodeWithText(expected).assertIsDisplayed()
    }

    @Test
    fun 카드_비밀번호_필드에_값을_입력하면_입력값이_표시된다() {
        // given
        composeTestRule.setContent { NewCardScreen() }

        // when
        val input = "0000" // 4자리

        composeTestRule
            .onNodeWithText("카드 비밀번호")
            .performTextInput(input)

        // then
        composeTestRule.onNodeWithText(input).assertIsDisplayed()
    }

    @Test
    fun 카드_비밀번호_필드에_최대_길이를_초과하여_값을_입력하면_제한된_길이까지_표시된다() {
        // given
        composeTestRule.setContent { NewCardScreen() }

        // when
        val input = "00000" // 5자리
        val expected = input.take(4)

        composeTestRule
            .onNodeWithText("카드 비밀번호")
            .performTextInput(input)

        // then
        composeTestRule.onNodeWithText(expected).assertIsDisplayed()
    }

    @Test
    fun 카드_비밀번호는_마스킹되어_표시된다() {
        // given
        composeTestRule.setContent { NewCardScreen() }

        // when
        val input = "0000"
        val expected = "••••"

        composeTestRule
            .onNodeWithText("카드 비밀번호")
            .performTextInput(input)

        // then
        composeTestRule.onNodeWithText(expected).assertIsDisplayed()
    }
}
