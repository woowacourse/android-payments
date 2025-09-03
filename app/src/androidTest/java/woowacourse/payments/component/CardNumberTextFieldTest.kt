package woowacourse.payments.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CardNumberTextFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            CardNumberTextField(
                cardNumber = "",
                onCardNumberChange = {},
                maxLength = 16
            )
        }
    }

    @Test
    fun 카드_번호_입력칸의_플레이스홀더는_0000_0000_0000_0000_이다() {
        composeTestRule.onNodeWithText("0000 – 0000 – 0000 – 0000")
            .assertIsDisplayed()
    }

    @Test
    fun 카드_번호_레이블이_보인다() {
        composeTestRule.onNodeWithText("카드 번호")
            .assertIsDisplayed()
    }
}
