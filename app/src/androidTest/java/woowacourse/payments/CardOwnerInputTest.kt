package woowacourse.payments

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.payments.domain.CardOwner
import woowacourse.payments.ui.component.CardOwnerInputField

class CardOwnerInputTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            CardOwnerInputField(
                cardOwner = CardOwner(""),
                onOwnerChange = { },
            )
        }
    }

    @Test
    fun 초기_화면에_카드_소유자_이름_텍스트가_표시된다() {
        // then
        composeTestRule
            .onNodeWithText("카드 소유자 이름(선택)")
            .assertIsDisplayed()
    }

    @Test
    fun 입력창을_클릭하면_라벨과_함께_placeholder가_표시된다() {
        // given

        // when
        composeTestRule
            .onNodeWithText("카드 소유자 이름(선택)")
            .performClick()

        // then
        composeTestRule
            .onNodeWithText("카드 소유자 이름(선택)")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("카드에 표시된 이름을 입력하세요.")
            .assertIsDisplayed()
    }
}
