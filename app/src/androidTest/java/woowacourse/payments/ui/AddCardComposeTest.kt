package woowacourse.payments.ui

import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.addcard.CardCreationScreen

class CardCreationScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            CardCreationScreen()
        }
    }

    @Test
    fun 카드_번호_입력_시_4글자당_하이픈이_생긴다() {
        // given
        val inputField = composeTestRule.onNodeWithText("카드 번호")

        // when
        val cardNumberInput = "1234567890123456"
        inputField.performTextInput(cardNumberInput)

        // then
        val expectedFormattedNumber = "1234-5678-9012-3456"
        composeTestRule.onNodeWithText(expectedFormattedNumber).assertExists()
    }

    @Test
    fun 카드_번호는_16자_까지만_입력할_수_있다() {
        // given
        val inputField = composeTestRule.onNodeWithText("카드 번호")

        // when
        val cardNumberInput = "123456789012345612344555667677456745684568"
        inputField.performTextInput(cardNumberInput)

        // then
        val expectedFormattedNumber = "1234-5678-9012-3456"
        composeTestRule.onNodeWithText(expectedFormattedNumber).assertExists()
    }

    @Test
    fun 카드_번호_입력_시_숫자_이외의_문자는_무시된다() {
        // given
        val inputField = composeTestRule.onNodeWithText("카드 번호")

        // when
        val cardNumberInput = "1가2나3다4라5마"
        inputField.performTextInput(cardNumberInput)

        // then
        val expectedFormattedNumber = "1234-5"
        composeTestRule.onNodeWithText(expectedFormattedNumber).assertExists()
    }

    @Test
    fun 만료일_입력_시_3번쨰_자리에_슬래시가_생긴다() {
        // given
        val inputField = composeTestRule.onNodeWithText("만료일")

        // when
        val expirationDateInput = "1234"
        inputField.performTextInput(expirationDateInput)

        // then
        val expectedFormattedNumber = "12/34"
        composeTestRule.onNodeWithText(expectedFormattedNumber).assertExists()
    }

    @Test
    fun 만료일은_4자_이상_입력되지_않는다() {
        // given
        val inputField = composeTestRule.onNodeWithText("만료일")

        // when
        val expirationDateInput = "123456789"
        inputField.performTextInput(expirationDateInput)

        // then
        val expectedFormattedNumber = "12/34"
        composeTestRule.onNodeWithText(expectedFormattedNumber).assertExists()
    }

    @Test
    fun 만료일_입력_시_숫자가_아닌_문자열은_무시된다() {
        // given
        val inputField = composeTestRule.onNodeWithText("만료일")

        // when
        val expirationDateInput = "1가2나3다4라"
        inputField.performTextInput(expirationDateInput)

        // then
        val expectedFormattedNumber = "12/34"
        composeTestRule.onNodeWithText(expectedFormattedNumber).assertExists()
    }

    @Test
    fun 만료일이_유효하지_않으면_오류로_표시된다() {
        // given
        val inputField = composeTestRule.onNodeWithText("만료일")

        // when
        val expirationDateInput = "9999"
        inputField.performTextInput(expirationDateInput)

        // then
        val hasErrorProperty =
            SemanticsMatcher.keyIsDefined(
                SemanticsProperties.Error,
            )
        inputField.assert(hasErrorProperty)
    }

    @Test
    fun 만료일이_과거_날짜이면_오류로_표시된다() {
        // given
        val inputField = composeTestRule.onNodeWithText("만료일")

        // when
        val expirationDateInput = "0203"
        inputField.performTextInput(expirationDateInput)

        // then
        val hasErrorProperty =
            SemanticsMatcher.keyIsDefined(
                SemanticsProperties.Error,
            )
        inputField.assert(hasErrorProperty)
    }

    @Test
    fun 카드_소유자_이름은_최대_30자_까지_작성된다() {
        // given
        val inputField = composeTestRule.onNodeWithText("카드 소유자 이름(선택)")

        // when
        val expirationDateInput = "a".repeat(99)
        inputField.performTextInput(expirationDateInput)

        // then
        val expectedFormattedNumber = "a".repeat(30)
        composeTestRule.onNodeWithText(expectedFormattedNumber).assertExists()
    }

    @Test
    fun 비밀번호는_자동_마스킹_처리된다() {
        // given
        val inputField = composeTestRule.onNodeWithText("비밀번호")

        // when
        val password = "0000"
        inputField.performTextInput(password)

        // then
        val expectedMaskedText = "••••"
        composeTestRule.onNodeWithText(expectedMaskedText).assertExists()
    }

    @Test
    fun 비밀번호는_최대_4자까지_입력된다() {
        // given
        val inputField = composeTestRule.onNodeWithText("비밀번호")

        // when
        val password = "0000000000000000000000"
        inputField.performTextInput(password)

        // then
        val expectedMaskedText = "••••"
        composeTestRule.onNodeWithText(expectedMaskedText).assertExists()
    }

    @Test
    fun 비밀번호_입력_시_숫자_이외의_문자는_무시된다() {
        // given
        val inputField = composeTestRule.onNodeWithText("비밀번호")

        // when
        val password = "1가2나다라마바사"
        inputField.performTextInput(password)

        // then
        val expectedMaskedText = "••"
        composeTestRule.onNodeWithText(expectedMaskedText).assertExists()
    }
}
