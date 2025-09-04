package woowacourse.payments

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.features.addcard.AddCardScreen
import woowacourse.payments.ui.theme.AndroidpaymentsTheme

class AddCardScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            AndroidpaymentsTheme {
                AddCardScreen(
                    onNavigateBack = { },
                    onNavigateSave = { },
                )
            }
        }
    }

    @Test
    fun 카드_추가_화면의_모든_입력_필드가_표시된다() {
        // then
        composeTestRule.onNodeWithText("카드 번호").assertExists()
        composeTestRule.onNodeWithText("만료일").assertExists()
        composeTestRule.onNodeWithText("카드 소유자 이름(선택)").assertExists()
        composeTestRule.onNodeWithText("비밀번호").assertExists()
    }

    @Test
    fun 카드_번호_입력_필드에_입력값이_구분자를_포함하여_표한된다() {
        // given
        val cardNumberField = composeTestRule.onNodeWithText("카드 번호")

        // when
        cardNumberField.performTextInput("1234123412341234")

        // then
        cardNumberField.assertExists()
        composeTestRule.onNodeWithText("1234 - 1234 - 1234 - 1234").assertIsDisplayed()
    }

    @Test
    fun 카드_번호_입력_필드에_값을_일부_입력시_입력값이_구분자를_포함하여_표한된다() {
        // given
        val cardNumberField = composeTestRule.onNodeWithText("카드 번호")

        // when
        cardNumberField.performTextInput("12341234")

        // then
        cardNumberField.assertExists()
        composeTestRule.onNodeWithText("1234 - 1234").assertIsDisplayed()
    }

    @Test
    fun 카드_소유자_이름을_입력하면_글자_수가_업데이트_된다() {
        // given
        val ownerNameField = composeTestRule.onNodeWithText("카드 소유자 이름(선택)")

        // when
        ownerNameField.performTextInput("WOOWA")

        // then
        composeTestRule.onNodeWithText("5/30").assertIsDisplayed()
    }

    @Test
    fun 유효한_만료일을_입력하면_에러_메시지가_표시되지_않는다() {
        // given
        val expireDateField = composeTestRule.onNodeWithText("만료일")

        // when
        expireDateField.performTextInput("1242")

        // then
        composeTestRule.onNodeWithText("유효하지 않은 만료일입니다").assertDoesNotExist()
    }

    @Test
    fun 유효하지_않은_만료일을_입력하면_에러_메시지가_표시된다() {
        // given
        val expireDateField = composeTestRule.onNodeWithText("만료일")

        // when
        expireDateField.performTextInput("1342")

        // then
        composeTestRule.onNodeWithText("유효하지 않은 월입니다").assertIsDisplayed()
    }

    @Test
    fun 만료일이_완전히_입력되지_않으면_에러_메시지가_표시되지_않는다() {
        // given
        val expireDateField = composeTestRule.onNodeWithText("만료일")

        // when
        expireDateField.performTextInput("999")

        // then
        composeTestRule.onNodeWithText("유효하지 않은 만료일입니다").assertDoesNotExist()
    }
}
