package woowacourse.payments.ui.common.component

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.screen.DEFAULT_CARD

class CardInfoContentTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Before
    fun setUp() {
        composeRule.setContent {
            CardInfoContent(
                card = DEFAULT_CARD,
                modifier = Modifier.Companion.testTag(TEST_TAG),
            )
        }
    }

    @Test
    fun `카드_번호에_구분자가_추가되며_뒤의_8자리는_가려진다`() {
        // then
        composeRule
            .onNodeWithContentDescription("카드 번호")
            .assert(hasText(DEFAULT_CARD.formatCardNumber()))
            .assertIsDisplayed()
    }

    @Test
    fun `만료일에_구분자가_추가된다`() {
        // then
        composeRule
            .onNodeWithContentDescription("카드 만료일")
            .assert(hasText(DEFAULT_CARD.formatExpiredDate()))
            .assertIsDisplayed()
    }

    @Test
    fun `소유자_이름이_출력된다`() {
        // then
        composeRule
            .onNodeWithContentDescription("카드 소유자 이름")
            .assert(hasText(DEFAULT_CARD.ownerName))
            .assertIsDisplayed()
    }

    companion object {
        private const val TEST_TAG = "TEST_TAG"
    }
}
