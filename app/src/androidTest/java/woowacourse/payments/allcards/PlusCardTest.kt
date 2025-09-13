package woowacourse.payments.allcards

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.allcards.component.PlusCard

@OptIn(ExperimentalTestApi::class)
class PlusCardTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 카드_추가_버튼을_클릭하면_카드_추가_동작을_수행한다() {
        // given
        var isClicked = false
        composeTestRule.setContent {
            PlusCard(
                onClick = {
                    isClicked = true
                },
            )
        }

        // when
        composeTestRule
            .onNodeWithContentDescription("추가")
            .performClick()

        // then
        assert(isClicked == true)
    }
}
