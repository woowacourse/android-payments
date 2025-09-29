package woowacourse.payments.addcard

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.ui.addcard.component.AddCardTopbar
import woowacourse.payments.ui.addcard.model.ModificationMode
import woowacourse.payments.ui.uimodel.CardInfoUiState

class AddCardTopbarTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 뒤로_가기_버튼을_누르면_뒤로_가기_동작을_수행한다() {
        // given
        var isBackClicked = false
        composeTestRule.setContent {
            AddCardTopbar(
                modificationMode = ModificationMode.Add(),
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
    fun 추가_모드에서_카드_추가가_가능할때_체크_버튼을_누르면_지정된_동작을_수행한다() {
        // given
        var isCompleteClicked = false
        composeTestRule.setContent {
            AddCardTopbar(
                modificationMode = ModificationMode.Add(),
                isAddCardEnabled = true,
                onAddCardSuccess = { isCompleteClicked = true },
            )
        }

        // when
        composeTestRule
            .onNodeWithContentDescription("확인")
            .performClick()

        // then
        assert(isCompleteClicked == true)
    }

    @Test
    fun 수정_모드에서_카드_추가와_수정이_가능할때_체크_버튼을_누르면_지정된_동작을_수행한다() {
        // given
        var isCompleteClicked = false
        composeTestRule.setContent {
            AddCardTopbar(
                modificationMode =
                    ModificationMode.Modify(
                        cardInfo = CardInfoUiState(),
                        id = 0,
                    ),
                isAddCardEnabled = true,
                isModificationEnabled = true,
                onModifyCardSuccess = { isCompleteClicked = true },
            )
        }

        // when
        composeTestRule
            .onNodeWithContentDescription("확인")
            .performClick()

        // then
        assert(isCompleteClicked == true)
    }

    @Test
    fun 수정_모드에서_카드_추가가_불가능하고_수정이_가능할떄_체크_버튼을_누르면_지정된_동작을_수행하지_않는다() {
        // given
        var isCompleteClicked = false
        composeTestRule.setContent {
            AddCardTopbar(
                modificationMode =
                    ModificationMode.Modify(
                        cardInfo = CardInfoUiState(),
                        id = 0,
                    ),
                isAddCardEnabled = false,
                isModificationEnabled = true,
                onModifyCardSuccess = { isCompleteClicked = true },
            )
        }

        // when
        composeTestRule
            .onNodeWithContentDescription("확인")
            .performClick()

        // then
        assert(isCompleteClicked == false)
    }

    @Test
    fun 수정_모드에서_카드_추가가_가능하고_수정이_불가능할때_체크_버튼을_누르면_지정된_동작을_수행하지_않는다() {
        // given
        var isCompleteClicked = false
        composeTestRule.setContent {
            AddCardTopbar(
                modificationMode =
                    ModificationMode.Modify(
                        cardInfo = CardInfoUiState(),
                        id = 0,
                    ),
                isAddCardEnabled = true,
                isModificationEnabled = false,
                onModifyCardSuccess = { isCompleteClicked = true },
            )
        }

        // when
        composeTestRule
            .onNodeWithContentDescription("확인")
            .performClick()

        // then
        assert(isCompleteClicked == false)
    }
}
