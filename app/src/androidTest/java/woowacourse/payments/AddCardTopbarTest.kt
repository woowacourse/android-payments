package woowacourse.payments

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.addcard.component.AddCardTopbar

@OptIn(ExperimentalTestApi::class)
class AddCardTopbarTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 뒤로_가기_버튼을_누르면_뒤로_가기_동작을_수행한다() {
        // given
        var isBackClicked = false
        composeTestRule.setContent {
            AddCardTopbar(
                onBackClick = { isBackClicked = true },
            )
        }

        // when
        composeTestRule
            .onNodeWithContentDescription("뒤로 가기")
            .performClick()

        // then
        assert(isBackClicked == true)
    }

    @Test
    fun 체크_버튼을_누르면_완료_동작을_수행한다() {
        // given
        var isCompleteClicked = false
        composeTestRule.setContent {
            AddCardTopbar(
                onBackClick = {},
                onCheckedClick = { isCompleteClicked = true },
            )
        }

        // when
        composeTestRule
            .onNodeWithContentDescription("확인")
            .performClick()

        // then
        assert(isCompleteClicked == true)
    }
}
