package woowacourse.payments.ui.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PaymentCardTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            PaymentCard(
                bankName = "국민카드",
                number = "1234 - 5678 - 1234 - 5678",
                expirationDate = "12 / 24",
                cardholderName = "JOHN DOE",
            )
        }
    }

    @Test
    fun `카드사_이름이_표시된다`() {
        composeTestRule
            .onNodeWithText("국민카드")
            .assertIsDisplayed()
    }

    @Test
    fun `카드번호가_표시된다`() {
        composeTestRule
            .onNodeWithText("1234 - 5678 - 1234 - 5678")
            .assertIsDisplayed()
    }

    @Test
    fun `만료일이_표시된다`() {
        composeTestRule
            .onNodeWithText("12 / 24")
            .assertIsDisplayed()
    }

    @Test
    fun `카드소유자명이_표시된다`() {
        composeTestRule
            .onNodeWithText("JOHN DOE")
            .assertIsDisplayed()
    }
}
