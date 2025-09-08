package woowacourse.payments.ui.payments.registration

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CardRegistrationScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent { CardRegistrationScreen() }
    }

    @Test
    fun `카드_번호와_만료일_그리고_비밀번호가_모두_유효한_경우_완료_버튼이_활성화된다`() {
        // given
        val cardNumber = "1234123412341234"
        val cardExpirationDate = "1299"
        val cardPassword = "1234"

        // when
        composeTestRule.onNode(hasContentDescription("카드 번호")).performTextInput(cardNumber)
        composeTestRule.onNode(hasContentDescription("만료일")).performTextInput(cardExpirationDate)
        composeTestRule.onNode(hasContentDescription("비밀번호")).performTextInput(cardPassword)

        // then
        composeTestRule.onNode(hasContentDescription("완료 버튼")).assertIsEnabled()
    }

    @Test
    fun `카드_번호가_유효하지_않은_경우_완료_버튼이_비활성화된다`() {
        // given
        val cardNumber = "1234"
        val cardExpirationDate = "1299"
        val cardPassword = "1234"

        // when
        composeTestRule.onNode(hasContentDescription("카드 번호")).performTextInput(cardNumber)
        composeTestRule.onNode(hasContentDescription("만료일")).performTextInput(cardExpirationDate)
        composeTestRule.onNode(hasContentDescription("비밀번호")).performTextInput(cardPassword)

        // then
        composeTestRule.onNode(hasContentDescription("완료 버튼")).assertIsNotEnabled()
    }

    @Test
    fun `카드_만료일이_유효하지_않은_경우_완료_버튼이_비활성화된다`() {
        // given
        val cardNumber = "1234123412341234"
        val cardExpirationDate = "1599"
        val cardPassword = "1234"

        // when
        composeTestRule.onNode(hasContentDescription("카드 번호")).performTextInput(cardNumber)
        composeTestRule.onNode(hasContentDescription("만료일")).performTextInput(cardExpirationDate)
        composeTestRule.onNode(hasContentDescription("비밀번호")).performTextInput(cardPassword)

        // then
        composeTestRule.onNode(hasContentDescription("완료 버튼")).assertIsNotEnabled()
    }

    @Test
    fun `카드_비밀번호가_유효하지_않은_경우_완료_버튼이_비활성화된다`() {
        // given
        val cardNumber = "1234123412341234"
        val cardExpirationDate = "1299"
        val cardPassword = "1"

        // when
        composeTestRule.onNode(hasContentDescription("카드 번호")).performTextInput(cardNumber)
        composeTestRule.onNode(hasContentDescription("만료일")).performTextInput(cardExpirationDate)
        composeTestRule.onNode(hasContentDescription("비밀번호")).performTextInput(cardPassword)

        // then
        composeTestRule.onNode(hasContentDescription("완료 버튼")).assertIsNotEnabled()
    }
}
