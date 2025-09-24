package woowacourse.payments

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.payments.ui.newcard.components.NewCardInputSection
import woowacourse.payments.ui.newcard.NewCardTestTag.CARD_EXPIRY_DATE_INPUT_TAG
import woowacourse.payments.ui.newcard.NewCardTestTag.CARD_NUMBERS_INPUT_TAG
import woowacourse.payments.ui.newcard.NewCardTestTag.CARD_OWNER_NAME_INPUT_TAG
import woowacourse.payments.ui.newcard.NewCardTestTag.CARD_PASSWORD_INPUT_TAG
import woowacourse.payments.ui.newcard.state.NewCardContentUiState

@RunWith(AndroidJUnit4::class)
class NewCardInputSectionTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private fun setContentWithState(initial: NewCardContentUiState = NewCardContentUiState()) {
        composeTestRule.setContent {
            var state by rememberSaveable { mutableStateOf(initial) }
            NewCardInputSection(
                newCardContentUiState = state,
                onCardNumbersChange = { state = state.copy(cardNumber = it) },
                onCardExpiryDateChange = { state = state.copy(expiryDate = it) },
                onCardOwnerNameChange = { state = state.copy(ownerName = it) },
                onCardPasswordChange = { state = state.copy(password = it) },
                modifier = Modifier,
            )
        }
    }

    @Test
    fun `카드번호_입력시_구분자가_포맷된다`() {
        setContentWithState()

        composeTestRule
            .onNodeWithTag(CARD_NUMBERS_INPUT_TAG)
            .performTextInput("1234567812345678")

        this@NewCardInputSectionTest.composeTestRule
            .onNodeWithText("1234-5678-1234-5678")
            .assertIsDisplayed()
    }

    @Test
    fun `만료일_입력시_구분자가_포맷된다`() {
        setContentWithState()

        composeTestRule
            .onNodeWithTag(CARD_EXPIRY_DATE_INPUT_TAG)
            .performTextInput("1229")

        composeTestRule.onNodeWithText("12/29").assertIsDisplayed()
    }

    @Test
    fun `잘못된_만료일이면_에러메시지가_보인다`() {
        val initial =
            NewCardContentUiState(
                cardNumber = "",
                expiryDate = "13/22",
                ownerName = "",
                password = "",
                expiryDateErrorTextRes = R.string.validate_card_expiry_invalid_month,
            )
        setContentWithState(initial)

        val errorText = "월은 01~12여야 합니다"
        composeTestRule.onNodeWithText(errorText).assertIsDisplayed()
    }

    @Test
    fun `카드소유자이름_길이가_입력될때마다_카운터가_업데이트된다`() {
        setContentWithState()

        composeTestRule.onNodeWithTag(CARD_OWNER_NAME_INPUT_TAG).performTextInput("12345")
        composeTestRule.onNodeWithText("5/30").assertIsDisplayed()
    }

    @Test
    fun `비밀번호는_숫자입력이_가능하고_마스킹된다`() {
        setContentWithState()
        composeTestRule
            .onNodeWithTag(CARD_PASSWORD_INPUT_TAG)
            .performTextInput("123")

        composeTestRule.onNodeWithText("•••").assertIsDisplayed()
    }
}
