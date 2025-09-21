package woowacourse.payments.cardregister

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.cardregister.CardRegisterScreen

class CardRegisterScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            CardRegisterScreen(
                onBackClick = { },
                onSaveClick = { },
            )
        }
    }

    @Test
    fun `카드번호를_입력하면_하이픈이_4자리마다_추가된다`() {
        // given
        val cardNumberField = composeTestRule.onNode(hasText("카드 번호") and hasSetTextAction())

        // when
        cardNumberField.performTextInput("1234567812345678")

        // then
        composeTestRule
            .onNodeWithText("1234-5678-1234-5678")
            .assertIsDisplayed()
    }

    @Test
    fun `카드번호는_숫자만_입력_가능하다`() {
        // given
        val cardNumberField = composeTestRule.onNode(hasText("카드 번호") and hasSetTextAction())

        // when
        cardNumberField.performTextInput("123412345678뭉치바보")

        // then
        composeTestRule
            .onNodeWithText("1234-1234-5678")
            .assertIsDisplayed()
    }

    @Test
    fun `만료일_입력시_구분자가_추가된다`() {
        // given
        val expiryDateField = composeTestRule.onNode(hasText("만료일") and hasSetTextAction())

        // when
        expiryDateField.performTextInput("1212")

        // then
        composeTestRule
            .onNodeWithText("12/12")
            .assertIsDisplayed()
    }

    @Test
    fun `만료일은_숫자만_입력_가능하다`() {
        // given
        val expiryDateField = composeTestRule.onNode(hasText("만료일") and hasSetTextAction())

        // when
        expiryDateField.performTextInput("121뭉")

        // then
        composeTestRule
            .onNodeWithText("12/1")
            .assertIsDisplayed()
    }

    @Test
    fun `카드_소유자_이름의_길이는_30자_이하이다`() {
        // given
        val cardOwnerField = composeTestRule.onNode(hasText("카드 소유자 이름(선택)") and hasSetTextAction())

        // when
        cardOwnerField.performTextInput("123456789012345678901234567890")
        cardOwnerField.performTextInput("1")

        // then
        composeTestRule
            .onNodeWithText("123456789012345678901234567890")
            .assertIsDisplayed()
    }

    @Test
    fun `카드_소유자_이름의_길이가_표시된다`() {
        // given
        val cardOwnerField = composeTestRule.onNode(hasText("카드 소유자 이름(선택)") and hasSetTextAction())

        // when
        cardOwnerField.performTextInput("뭉치바보")

        // then
        composeTestRule
            .onNodeWithText("4/30")
            .assertIsDisplayed()
    }

    @Test
    fun `비밀번호_입력_시_노출되지_않는다`() {
        // given
        val passwordField = composeTestRule.onNode(hasText("비밀번호") and hasSetTextAction())
        // when
        passwordField.performTextInput("1234")

        // then
        composeTestRule.onNodeWithText("••••").assertIsDisplayed()
    }

    @Test
    fun `비밀번호는_숫자만_입력_가능하다`() {
        // given
        val passwordField = composeTestRule.onNode(hasText("비밀번호") and hasSetTextAction())

        // when
        passwordField.performTextInput("123뭉")

        // then
        composeTestRule
            .onNodeWithText("123")
            .assertIsDisplayed()
    }
}
