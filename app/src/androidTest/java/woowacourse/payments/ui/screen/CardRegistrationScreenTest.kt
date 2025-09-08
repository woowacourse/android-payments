package woowacourse.payments.ui.screen

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.payments.screen.CardRegistrationScreen

class CardRegistrationScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent { CardRegistrationScreen() }
    }

    @Test
    fun `카드_번호와_만료일_그리고_비밀번호가_모두_유효한_경우_등록_버튼이_활성화된다`() {
        // given
        val cardNumber = "1234123412341234"
        val cardExpirationDate = "1299"
        val cardPassword = "1234"

        // when
        composeTestRule.onNodeWithTag("CardNumberTextField").performTextInput(cardNumber)
        composeTestRule.onNodeWithTag("CardExpirationDateTextField").performTextInput(cardExpirationDate)
        composeTestRule.onNodeWithTag("CardPasswordTextField").performTextInput(cardPassword)

        // then
        composeTestRule
            .onNodeWithTag("CardRegistrationTopBarRegistrationButtonTestTag")
            .assertIsEnabled()
    }

    @Test
    fun `카드_번호가_유효하지_않은_경우_등록_버튼이_비활성화된다`() {
        // given
        val cardNumber = "1234"
        val cardExpirationDate = "1299"
        val cardPassword = "1234"

        // when
        composeTestRule.onNodeWithTag("CardNumberTextField").performTextInput(cardNumber)
        composeTestRule
            .onNodeWithTag("CardExpirationDateTextField")
            .performTextInput(cardExpirationDate)
        composeTestRule.onNodeWithTag("CardPasswordTextField").performTextInput(cardPassword)

        // then
        composeTestRule
            .onNodeWithTag("CardRegistrationTopBarRegistrationButtonTestTag")
            .assertIsNotEnabled()
    }

    @Test
    fun `카드_만료일이_유효하지_않은_경우_등록_버튼이_비활성화된다`() {
        // given
        val cardNumber = "1234123412341234"
        val cardExpirationDate = "1599"
        val cardPassword = "1234"

        // when
        composeTestRule.onNodeWithTag("CardNumberTextField").performTextInput(cardNumber)
        composeTestRule
            .onNodeWithTag("CardExpirationDateTextField")
            .performTextInput(cardExpirationDate)
        composeTestRule.onNodeWithTag("CardPasswordTextField").performTextInput(cardPassword)

        // then
        composeTestRule
            .onNodeWithTag("CardRegistrationTopBarRegistrationButtonTestTag")
            .assertIsNotEnabled()
    }

    @Test
    fun `카드_비밀번호가_유효하지_않은_경우_등록_버튼이_비활성화된다`() {
        // given
        val cardNumber = "1234123412341234"
        val cardExpirationDate = "1299"
        val cardPassword = "1"

        // when
        composeTestRule.onNodeWithTag("CardNumberTextField").performTextInput(cardNumber)
        composeTestRule
            .onNodeWithTag("CardExpirationDateTextField")
            .performTextInput(cardExpirationDate)
        composeTestRule.onNodeWithTag("CardPasswordTextField").performTextInput(cardPassword)

        // then
        composeTestRule
            .onNodeWithTag("CardRegistrationTopBarRegistrationButtonTestTag")
            .assertIsNotEnabled()
    }
}