package woowacourse.payments.component

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.component.NewCardName

class NewCardNameTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun `카드사명이_보인다`() {
        // given
        val companyName = "신한카드"

        // when
        composeTestRule.setContent {
            NewCardName(companyName)
        }

        // then
        composeTestRule.onNodeWithText(companyName).assertIsDisplayed()
    }
}
