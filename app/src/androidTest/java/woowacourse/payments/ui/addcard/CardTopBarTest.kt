package woowacourse.payments.ui.addcard

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.R

class CardTopBarTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private fun setupCardTopBar(
        titleResId: Int = R.string.add_card_tool_bar_title,
        onBackClick: () -> Unit = {},
        onSaveClick: () -> Unit = {},
        isOnSaveClickable: Boolean = false,
    ) {
        composeTestRule.setContent {
            CardTopBar(
                titleResId = titleResId,
                onBackClick = onBackClick,
                onSaveClick = onSaveClick,
                isOnSaveClickable = isOnSaveClickable,
            )
        }
    }

    @Test
    fun `CardTopBar_에_올바른_제목이_표시된다`() {
        // given + when
        setupCardTopBar(titleResId = R.string.add_card_tool_bar_title)

        // then
        composeTestRule.onNodeWithText("카드 추가").assertIsDisplayed()
    }

    @Test
    fun `뒤로가기_버튼_클릭_시_onBackClick이_호출된다`() {
        // given
        var backClicked = false
        setupCardTopBar(onBackClick = { backClicked = true })

        // when
        composeTestRule.onNodeWithContentDescription("뒤로 가기").performClick()

        // then
        assert(backClicked)
    }

    @Test
    fun `저장_버튼_클릭_시_isOnSaveClickable이_true이면_onSaveClick이_호출된다`() {
        // given
        var saveClicked = false
        setupCardTopBar(onSaveClick = { saveClicked = true }, isOnSaveClickable = true)

        // when
        composeTestRule.onNodeWithContentDescription("저장").performClick()

        // then
        assert(saveClicked)
    }

    @Test
    fun `저장_버튼_클릭_시_isOnSaveClickable이_false이면_onSaveClick이_호출되지_않는다`() {
        // given
        var saveClicked = false
        setupCardTopBar(onSaveClick = { saveClicked = true }, isOnSaveClickable = false)

        // when
        composeTestRule.onNodeWithContentDescription("저장").performClick()

        // then
        assert(!saveClicked)
    }

    @Test
    fun `isOnSaveClickable이_true이면_저장_버튼이_활성화된다`() {
        // given + when
        setupCardTopBar(isOnSaveClickable = true)

        // then
        composeTestRule.onNodeWithContentDescription("저장").assertIsEnabled()
    }

    @Test
    fun `isOnSaveClickable이_false이면_저장_버튼이_비활성화된다`() {
        // given + when
        setupCardTopBar(isOnSaveClickable = false)

        // then
        composeTestRule.onNodeWithContentDescription("저장").assertIsNotEnabled()
    }
}
