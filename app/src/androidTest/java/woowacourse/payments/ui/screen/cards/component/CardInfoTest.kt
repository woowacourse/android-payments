package woowacourse.payments.ui.screen.cards.component

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.screen.DEFAULT_CARD

class CardInfoTest {
    @get:Rule
    val composeRule =    createComposeRule()

    @Before
    fun setUp() {
        composeRule.setContent {
            CardInfo(
                card = DEFAULT_CARD,
                modifier = Modifier.testTag(TEST_TAG)
            )
        }
    }

    @Test
    fun 카드_번호에_구분자가_추가되며_뒤의_8자리는_가려진다() {
        // then
        composeRule
            .onNodeWithText("1234 - 5678 - **** - ****")
            .assertIsDisplayed()
    }

    @Test
    fun 만료일에_구분자가_추가된다() {
        // then
        composeRule
            .onNodeWithText("09 / 25")
            .assertIsDisplayed()
    }

    @Test
    fun 소유자_이름이_출력된다() {
        // then
        composeRule
            .onNodeWithText("INHYEOP LEE")
            .assertIsDisplayed()
    }

    companion object {
        private const val TEST_TAG = "TEST_TAG"
    }
}
