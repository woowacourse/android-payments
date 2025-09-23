package woowacourse.payments.addcard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performKeyInput
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.pressKey
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.domain.model.CardInfo
import woowacourse.payments.ui.addcard.AddCardScreen
import woowacourse.payments.ui.addcard.component.CardNumberTextField
import woowacourse.payments.ui.addcard.component.ExpireDateTextField
import woowacourse.payments.ui.addcard.component.OwnerNameTextField
import woowacourse.payments.ui.addcard.component.PasswordTextField
import woowacourse.payments.ui.addcard.model.VendorModalUiState
import woowacourse.payments.ui.uimodel.CardInfoUiModel
import woowacourse.payments.ui.uimodel.CardInfoUiState
import woowacourse.payments.ui.uimodel.VendorUiModel

@OptIn(ExperimentalTestApi::class)
class AddCardScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 카드_번호는_유효한_자리까지_입력할_수_있다() {
        // given
        composeTestRule.setContent {
            var state by remember {
                mutableStateOf(
                    CardInfoUiState(
                        CardInfoUiModel(
                            cardNumber = "1234123412341234",
                        ),
                    ),
                )
            }
            CardNumberTextField(state, Modifier.Companion)
        }
        val rawCardNumber = "12341234123412345"
        val expected =
            CardInfo.Companion
                .formatCardNumber(rawCardNumber)
                .chunked(4)
                .joinToString("-")

        // when
        composeTestRule
            .onNodeWithText("카드 번호")
            .performTextInput(rawCardNumber)

        // then
        composeTestRule
            .onNodeWithText("카드 번호")
            .assertTextContains(expected)
    }

    @Test
    fun 카드_번호를_입력하면_4자리마다_하이픈이_출력된다() {
        // given
        composeTestRule.setContent {
            var state by remember { mutableStateOf(CardInfoUiState()) }
            CardNumberTextField(state, Modifier.Companion)
        }

        // when
        composeTestRule
            .onNodeWithText("카드 번호")
            .performClick()
            .performTextInput("12341234")

        // then
        composeTestRule
            .onNodeWithText("카드 번호")
            .assertTextContains("1234-1234")
    }

    @Test
    fun 카드_번호_필드에서_문자를_지우면_하이픈이_함께_제거된다() {
        // given
        composeTestRule.setContent {
            var state by remember {
                mutableStateOf(
                    CardInfoUiState(
                        CardInfoUiModel(
                            cardNumber = "12341",
                        ),
                    ),
                )
            }
            CardNumberTextField(state)
        }

        // when
        composeTestRule
            .onNodeWithText("카드 번호")
            .performClick()
            .performKeyInput {
                pressKey(Key.Backspace)
            }

        // then
        composeTestRule
            .onNodeWithText("-", substring = true)
            .assertDoesNotExist()
    }

    @Test
    fun 만료일은_유효한_자리와_숫자만_입력할_수_있다() {
        // given
        composeTestRule.setContent {
            var state by remember { mutableStateOf(CardInfoUiState()) }
            ExpireDateTextField(state, Modifier.Companion)
        }
        val rawExpireDate = "12345"
        val expected =
            CardInfo.Companion
                .formatExpireDate(rawExpireDate)
                .chunked(2)
                .joinToString("/")

        // when
        composeTestRule
            .onNodeWithText("만료일")
            .performTextInput(rawExpireDate)

        // then
        composeTestRule
            .onNodeWithText("만료일")
            .assertTextContains(expected)
    }

    @Test
    fun 만료일의_월은_유효한_값이_아니면_오류를_출력한다() {
        // given
        composeTestRule.setContent {
            var state by remember { mutableStateOf(CardInfoUiState()) }
            ExpireDateTextField(state, Modifier.Companion)
        }
        val rawExpireDate = "13"
        val isValid = CardInfo.Companion.checkIsValidMonth(rawExpireDate)

        // when
        composeTestRule
            .onNodeWithText("만료일")
            .performTextInput(rawExpireDate)

        // then
        if (!isValid) {
            composeTestRule
                .onNodeWithText("유효하지 않은 날짜입니다")
                .assertIsDisplayed()
        } else {
            composeTestRule
                .onNodeWithText("유효하지 않은 날짜입니다")
                .assertDoesNotExist()
        }
    }

    @Test
    fun 만료일_필드에서_월_두_자리를_입력하면_슬래시가_출력된다() {
        // given
        composeTestRule.setContent {
            var state by remember { mutableStateOf(CardInfoUiState()) }
            ExpireDateTextField(state, Modifier.Companion)
        }

        // when
        composeTestRule
            .onNodeWithText("만료일")
            .performClick()
            .performTextInput("123")

        // then
        composeTestRule
            .onNodeWithText("만료일")
            .assertTextContains("12/3")
    }

    @Test
    fun 카드_소유자_필드는_유효한_자리까지_입력할_수_있다() {
        // given
        composeTestRule.setContent {
            var state by remember { mutableStateOf(CardInfoUiState()) }
            OwnerNameTextField(state, Modifier.Companion)
        }
        val rawOwnerName = "12345678901234567890123456789012345678901234567890"
        val expected = CardInfo.Companion.formatOwnerName(rawOwnerName)

        // when
        composeTestRule
            .onNodeWithText("카드 소유자 이름(선택)")
            .performClick()
            .performTextInput(rawOwnerName)

        // then
        composeTestRule
            .onNodeWithText("카드 소유자 이름(선택)")
            .assertTextContains(expected)
    }

    @Test
    fun 카드_소유자_필드에서_현재_입력값의_길이를_표시할_수_있다() {
        // given
        composeTestRule.setContent {
            var state by remember { mutableStateOf(CardInfoUiState()) }
            OwnerNameTextField(state, Modifier.Companion)
        }

        // when
        composeTestRule
            .onNodeWithText("카드 소유자 이름(선택)")
            .performClick()
            .performTextInput("12345678901234567890")

        // then
        composeTestRule
            .onNodeWithText("20/30")
            .assertIsDisplayed()
    }

    @Test
    fun 비밀번호는_유효한_글자만_입력할_수_있다() {
        // given
        composeTestRule.setContent {
            var state by remember { mutableStateOf(CardInfoUiState()) }
            PasswordTextField(state, Modifier.Companion)
        }
        val rawPassword = "1234a"
        val expected = CardInfo.Companion.formatPassword(rawPassword)

        // when
        composeTestRule
            .onNodeWithText("비밀번호")
            .performTextInput(rawPassword)

        // then
        composeTestRule
            .onNodeWithText("비밀번호")
            .assertTextContains(expected)
    }

    @Test
    fun 비밀번호는_유효한_자리만_입력할_수_있다() {
        // given
        composeTestRule.setContent {
            var state by remember { mutableStateOf(CardInfoUiState()) }
            PasswordTextField(state, Modifier.Companion)
        }
        val rawPassword = "1234567890"
        val expected = CardInfo.Companion.formatPassword(rawPassword)

        // when
        composeTestRule
            .onNodeWithText("비밀번호")
            .performTextInput(rawPassword)

        // then
        composeTestRule
            .onNodeWithText("비밀번호")
            .assertTextContains(expected)
    }

    @Test
    fun 비밀번호는_마스킹_처리되어_출력한다() {
        // given
        composeTestRule.setContent {
            var state by remember { mutableStateOf(CardInfoUiState()) }
            PasswordTextField(state, Modifier.Companion)
        }

        // when
        composeTestRule
            .onNodeWithText("비밀번호")
            .performClick()
            .performTextInput("1")

        // then
        composeTestRule
            .onNodeWithText("비밀번호")
            .assertTextContains('\u2022'.toString())
    }
}
