package woowacourse.payments.ui.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.Card

class PaymentCardTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Before
    fun setUp() {
        composeRule.setContent {
            PaymentCard(
                detail =
                    Card(
                        number = "1234".repeat(4),
                        owner = "CREW",
                        expiredDate = "0421",
                    ),
            )
        }
    }

    @Test
    fun `카드_번호의_뒷_8자리는_가려져_표시된다`() {
        composeRule
            .onNodeWithText("1234 - 1234 - **** - ****")
            .assertIsDisplayed()
    }

    @Test
    fun `카드_소유자_이름이_표시된다`() {
        composeRule
            .onNodeWithText("CREW")
            .assertIsDisplayed()
    }

    @Test
    fun `만료일은_월_년도가_구분자로_구분되어_표시된다`() {
        composeRule
            .onNodeWithText("04 / 21")
            .assertIsDisplayed()
    }
}
