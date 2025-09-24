package woowacourse.payments.ui.newcard

import androidx.compose.runtime.remember
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.HWANNOW_CARD
import woowacourse.payments.ui.updateInitialCard

class NewCardScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `수정_모드에서_값이_바뀌지_않으면_저장_버튼이_뜨지_않는다`() {
        // given & when
        composeTestRule.setContent {
            NewCardScreen(
                banks = emptyList(),
                initialCard = HWANNOW_CARD,
                newCardStateHolder =
                    remember {
                        NewCardStateHolder().apply {
                            updateInitialCard(
                                HWANNOW_CARD,
                            )
                        }
                    },
                onBackPress = { },
                onSaved = { },
            )
        }

        // then
        composeTestRule
            .onNodeWithContentDescription("완료")
            .assertDoesNotExist()
    }

    @Test
    fun `수정_모드에서_값이_바뀌면_저장할_수_있다`() {
        // given
        var isSaved = false
        val stateHolder = NewCardStateHolder()
        composeTestRule.setContent {
            NewCardScreen(
                banks = emptyList(),
                initialCard = HWANNOW_CARD,
                newCardStateHolder = remember { stateHolder },
                onBackPress = { },
                onSaved = { isSaved = true },
            )
        }

        // when
        stateHolder.updateCardHolder("김환노")

        composeTestRule
            .onNodeWithContentDescription("완료")
            .performClick()

        // then
        isSaved = true
    }
}
