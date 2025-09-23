package woowacourse.payments.ui.cardlist.composable

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.domain.BankType
import woowacourse.payments.domain.Card
import woowacourse.payments.domain.CardExpirationDate
import woowacourse.payments.domain.CardNumber
import woowacourse.payments.domain.OwnerName
import woowacourse.payments.domain.Password
import woowacourse.payments.ui.model.BankTypeUiModel

class RegisterPaymentCardTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private fun setupRegisterPaymentCard(
        card: Card =
            Card(
                bank = BankType.BC,
                number = CardNumber.fromRawInput("1234123412341234"),
                expirationDate = CardExpirationDate.fromRawInput("12/34"),
                ownerName = OwnerName.fromRawInput("Sia"),
                password = Password.fromRawInput("12"),
            ),
    ) {
        composeTestRule.setContent {
            RegisterPaymentCard(card = card.toUiModel())
        }
    }

    @Test
    fun `은행_이름이_올바르게_표시된다`() {
        // given + when
        setupRegisterPaymentCard()

        // then
        composeTestRule.onNodeWithText("BC카드").assertIsDisplayed()
    }

    @Test
    fun `마스킹된_카드_번호가_표시된다`() {
        // given + when
        setupRegisterPaymentCard()

        // then
        composeTestRule.onNodeWithText("1234 - 1234 - **** - ****").assertIsDisplayed()
    }

    @Test
    fun `소유자_이름이_올바르게_표시된다`() {
        // given + when
        setupRegisterPaymentCard()

        // then
        composeTestRule.onNodeWithText("Sia").assertIsDisplayed()
    }

    @Test
    fun `만료일이_올바르게_표시된다`() {
        // given + when
        setupRegisterPaymentCard()

        // then
        composeTestRule.onNodeWithText("12/34").assertIsDisplayed()
    }
}
