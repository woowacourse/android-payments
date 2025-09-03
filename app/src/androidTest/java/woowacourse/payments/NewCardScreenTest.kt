package woowacourse.payments

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NewCardScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            NewCardScreen()
        }
    }

    @Test fun 카드번호_입력필드의_레이블과_플레이스홀더가_보인다() {
        composeTestRule
            .onNodeWithText("카드 번호")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("0000 – 0000 – 0000 – 0000")
            .assertIsDisplayed()
    }
}
