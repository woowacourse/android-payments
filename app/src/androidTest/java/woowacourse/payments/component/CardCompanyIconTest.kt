package woowacourse.payments.component

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import woowacourse.payments.R
import woowacourse.payments.domain.Banks
import woowacourse.payments.ui.component.CardCompanyIcon

class CardCompanyIconTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun `은행_아이콘과_이름이_보인다`() {
        // given
        composeTestRule.setContent {
            CardCompanyIcon(
                bankIcon = R.drawable.ic_bc,
                bankName = "우리은행",
                banks = Banks.BC,
                onClick = {},
            )
        }

        // then
        composeTestRule
            .onNodeWithContentDescription("우리은행")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("우리은행")
            .assertIsDisplayed()
    }

    @Test
    fun `은행_아이콘을_클릭하면_콜백이_호출된다`() {
        // given
        var clickedBank: Banks? = null

        composeTestRule.setContent {
            CardCompanyIcon(
                bankIcon = R.drawable.ic_bc,
                bankName = "우리은행",
                banks = Banks.BC,
                onClick = { clickedBank = it },
            )
        }

        // when
        composeTestRule
            .onNodeWithContentDescription("우리은행")
            .performClick()

        // then
        assertEquals(clickedBank, Banks.BC)
    }
}
