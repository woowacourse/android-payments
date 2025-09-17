package woowacourse.payments.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.domain.CardCompany
import woowacourse.payments.domain.Card
import woowacourse.payments.ui.component.RegisteredCard
import woowacourse.payments.ui.state.BankState

class RegisteredCardTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var card: Card

    @Before
    fun setUp() {
        card =
            Card(
                number = "1111222233334444",
                expireDate = "0421",
                ownerName = "peto",
                password = "",
                BankState.Bank(CardCompany.BC),
            )

        composeTestRule.setContent {
            RegisteredCard(
                card,
                4,
                " - ",
                "*",
                2,
                " / ",
            )
        }
    }

    @Test
    fun `마스킹된_카드_번호가_보인다`() {
        composeTestRule.onNodeWithText("1111 - 2222 - **** - ****").assertIsDisplayed()
    }

    @Test
    fun `카드_주인_이름이_보인다`() {
        composeTestRule.onNodeWithText("peto").assertIsDisplayed()
    }

    @Test
    fun `카드_만료일이_보인다`() {
        composeTestRule.onNodeWithText("04 / 21").assertIsDisplayed()
    }
}
