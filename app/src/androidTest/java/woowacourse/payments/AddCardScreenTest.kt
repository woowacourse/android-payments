package woowacourse.payments

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasText
import org.junit.Rule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performKeyInput
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTextReplacement
import androidx.compose.ui.test.pressKey
import androidx.lifecycle.viewmodel.CreationExtras.Companion.Key
import org.junit.After
import org.junit.Before
import org.junit.Test
import woowacourse.payments.ui.addcard.AddCardContent
import woowacourse.payments.ui.addcard.AddCardScreen
import woowacourse.payments.ui.addcard.CardInfoUiState
import woowacourse.payments.ui.addcard.CardNumberTextField
import woowacourse.payments.ui.addcard.ExpireDateTextField
import woowacourse.payments.ui.addcard.OwnerNameTextField
import woowacourse.payments.ui.addcard.PasswordTextField

@OptIn(ExperimentalTestApi::class)
class AddCardScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun 카드_번호는_16자리까지_입력할_수_있다() {
        //given
        composeTestRule.setContent {
            var state by remember { mutableStateOf(CardInfoUiState()) }
            CardNumberTextField(Modifier, state)
        }

        //when
        composeTestRule
            .onNodeWithText("카드 번호")
            .performTextInput("12341234123412345")

        //then
        composeTestRule
            .onNodeWithText("카드 번호")
            .assertTextContains("1234-1234-1234-1234")
    }

    @Test
    fun 카드_번호를_입력하면_4자리마다_하이픈이_생성된다() {
        //given
        composeTestRule.setContent {
            var state by remember { mutableStateOf(CardInfoUiState()) }
            CardNumberTextField(Modifier, state)
        }

        //when
        composeTestRule
            .onNodeWithText("카드 번호")
            .performClick()
            .performTextInput("12341234")

        //then
        composeTestRule
            .onNodeWithText("카드 번호")
            .assertTextContains("1234-1234")
    }

    @Test
    fun 카드_번호_필드에서_문자를_지우면_하이픈이_함께_제거된다() {
        //given
        composeTestRule.setContent {
            var state by remember { mutableStateOf(CardInfoUiState(
                cardNumber = "12341"
            )) }
            CardNumberTextField(Modifier, state)
        }

        //when
        composeTestRule
            .onNodeWithText("카드 번호")
            .performClick()
            .performKeyInput {
                pressKey(Key.Backspace)
            }

        //then
        composeTestRule
            .onNodeWithText("카드 번호")
            .assertTextContains("1234")
    }

    @Test
    fun 만료일은_4자리까지_입력할_수_있다() {
        //given
        composeTestRule.setContent {
            var state by remember { mutableStateOf(CardInfoUiState()) }
            ExpireDateTextField(Modifier, state)
        }

        //when
        composeTestRule
            .onNodeWithText("만료일")
            .performTextInput("12345")

        //then
        composeTestRule
            .onNodeWithText("만료일")
            .assertTextContains("1234")

    }

    @Test
    fun 만료일은_숫자만_입력할_수_있다() {
        //given
        composeTestRule.setContent {
            var state by remember { mutableStateOf(CardInfoUiState()) }
            ExpireDateTextField(Modifier, state)
        }

        //when
        composeTestRule
            .onNodeWithText("만료일")
            .performTextInput("1234a")

        //then
        composeTestRule
            .onNodeWithText("만료일")
            .assertTextContains("1234")

    }

    @Test
    fun 만료일의_월은_01부터_12까지의_값이_아니면_오류를_표시한다() {
        //given
        composeTestRule.setContent {
            var state by remember { mutableStateOf(CardInfoUiState()) }
            ExpireDateTextField(Modifier, state)
        }

        //when
        composeTestRule
            .onNodeWithText("만료일")
            .performTextInput("13")

        //then
        composeTestRule
            .onNodeWithText("유효하지 않은 날짜입니다")
            .assertIsDisplayed()

    }

    @Test
    fun 만료일_필드에서_월_두_자리를_입력하면_슬래시가_생성된다() {
        //given
        composeTestRule.setContent {
            var state by remember { mutableStateOf(CardInfoUiState()) }
            ExpireDateTextField(Modifier, state)
        }

        //when
        composeTestRule
            .onNodeWithText("만료일")
            .performClick()
            .performTextInput("123")

        //then
        composeTestRule
            .onNodeWithText("만료일")
            .assertTextContains("12/3")

    }

    @Test
    fun 카드_소유자_필드는_30자_까지_입력할_수_있다() {
        //given
        composeTestRule.setContent {
            var state by remember { mutableStateOf(CardInfoUiState()) }
            OwnerNameTextField(Modifier, state)
        }

        //when
        composeTestRule
            .onNodeWithText("카드 소유자 이름(선택)")
            .performClick()
            .performTextInput("123456789012345678901234567890123")

        //then
        composeTestRule
            .onNodeWithText("카드 소유자 이름(선택)")
            .assertTextContains("123456789012345678901234567890")
    }

    @Test
    fun 카드_소유자_필드에서_현재_입력값의_길이를_표시할_수_있다() {
        //given
        composeTestRule.setContent {
            var state by remember { mutableStateOf(CardInfoUiState()) }
            OwnerNameTextField(Modifier, state)
        }

        //when
        composeTestRule
            .onNodeWithText("카드 소유자 이름(선택)")
            .performClick()
            .performTextInput("12345678901234567890")

        //then
        composeTestRule
            .onNodeWithText("20/30")
            .assertIsDisplayed()
    }

    @Test
    fun 비밀번호는_숫자만_입력할_수_있다() {
        //given
        composeTestRule.setContent {
            var state by remember { mutableStateOf(CardInfoUiState()) }
            PasswordTextField(Modifier, state)
        }

        //when
        composeTestRule
            .onNodeWithText("비밀번호")
            .performTextInput("1234a")

        //then
        composeTestRule
            .onNodeWithText("비밀번호")
            .assertTextContains("1234")

    }

    @Test
    fun 비밀번호는_4자리만_입력할_수_있다() {
        //given
        composeTestRule.setContent {
            var state by remember { mutableStateOf(CardInfoUiState()) }
            PasswordTextField(Modifier, state)
        }

        //when
        composeTestRule
            .onNodeWithText("비밀번호")
            .performTextInput("12345")

        //then
        composeTestRule
            .onNodeWithText("비밀번호")
            .assertTextContains("1234")
    }

    @Test
    fun 비밀번호는_마스킹_처리되어_보여진다() {
        //given
        composeTestRule.setContent {
            var state by remember { mutableStateOf(CardInfoUiState()) }
            PasswordTextField(Modifier, state)
        }

        //when
        composeTestRule
            .onNodeWithText("비밀번호")
            .performClick()
            .performTextInput("1")

        //then
        composeTestRule
            .onNodeWithText("비밀번호")
            .assertTextContains('\u2022'.toString())

    }
}
